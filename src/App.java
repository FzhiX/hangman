import classes.*;
import processing.core.*;

public class App extends PApplet {

  Hangman game;

  public static void main(String[] args) {
    String[] processingArgs = { "MySketch" };
    App mySketch = new App();
    PApplet.runSketch(processingArgs, mySketch);
  }

  public void settings() {
    size(600, 400);
  }

  public void setup() {
    game = new Hangman("Multiplication");
  }

  public void draw() {
    background(0);
    game.drawGuessedLetters(this);
  }

  public void keyPressed() {
    if (key != CODED) {
      game.guess(key);
    }
  }
}
