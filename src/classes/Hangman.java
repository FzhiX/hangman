package classes;

import processing.core.PApplet;
import processing.core.PConstants;

public class Hangman {

  private String secretWord, drawWord;
  private char[] guessedLetters;
  private int lives;
  private int[] lettersIndex;

  public Hangman(String word) {
    secretWord = word;
    drawWord = word;
    secretWord = secretWord.toLowerCase();
    guessedLetters = new char[secretWord.length()];

    lives = 6;

    lettersIndex = new int[255];
    for (int i = 0; i < 255; i++) {
      lettersIndex[i] = 0;
    }

    //init partial word, and set all
    //letters to '_'
    guessedLetters = new char[word.length()];
    for (int i = 0; i < guessedLetters.length; i++) {
      guessedLetters[i] = '_';
    }
  }

  public char[] getGuessedLetters() {
    return guessedLetters;
  }

  public boolean guess(char letter) {
    if (lives <= 0) {
      return false;
    }

    boolean found = false;
    letter = Character.toLowerCase(letter);

    for (int i = 0; i < secretWord.length(); i++) {
      if (secretWord.charAt(i) == letter) {
        guessedLetters[i] = letter;
        found = true;
      }
    }

    if (found == false) {
      if (lettersIndex[letter] == 1) {
        return false;
      }
      lives--;
      lettersIndex[letter] = 1;
    }

    return found;
  }

  public void drawGuessedLetters(PApplet p) {
    //draw word on screen
    p.textSize(32);
    for (int i = 0; i < guessedLetters.length; i++) {
      //draw guessed letter in center of the screen
      p.text(
        guessedLetters[i],
        p.width / 2 + 20 * i - guessedLetters.length * 20 / 2,
        p.height / 2
      );
    }
    drawGameState(p);
  }

  public boolean isAlive() {
    if (lives <= 0) {
      return false;
    } else {
      return true;
    }
  }

  private void drawGameState(PApplet p) {
    p.textSize(32);
    p.textAlign(PConstants.CENTER, PConstants.CENTER);

    if (isAlive() == true) {
      p.text("Lives: " + lives, p.width / 2, p.height / 2);
    } else if (isAlive() == false) {
      p.text("Game Over", p.width / 2, p.height / 2 + 40);
    }
  }
}
