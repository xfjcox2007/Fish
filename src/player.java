import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;

// a class that represents the fish
class Fish {
  Posn center;
  int radius;
  Color color;

  // constructor
  Fish(Posn center, int radius, Color color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }

  // to produce the image of the PlayerFish
  WorldImage fishImage() {
    return new CircleImage(this.radius, OutlineMode.SOLID, Color.RED);
  }

  // ON KEY EVENT
  // to move the PlayerFish 5 pixels in the direction given by the key
  public Fish moveFish(String key) {
    if (key.equals("right")) {
      return new Fish(new Posn(this.center.x + 5, this.center.y), this.radius, this.color);
    }
    else if (key.equals("left")) {
      return new Fish(new Posn(this.center.x - 5, this.center.y), this.radius, this.color);
    }
    else if (key.equals("up")) {
      return new Fish(new Posn(this.center.x, this.center.y - 5), this.radius, this.color);
    }
    else if (key.equals("down")) {
      return new Fish(new Posn(this.center.x, this.center.y + 5), this.radius, this.color);
    }
    else {
      return this;
    }
  }

  // ON TICK
  // the player fish can loop around
  Fish reEnter(int w, int h) {
    if (this.center.x == w) {
      return new Fish(new Posn(0, this.center.y), this.radius, this.color);
    }
    if (this.center.x == 0) {
      return new Fish(new Posn(w, this.center.y), this.radius, this.color);
    }
    else {
      return this;
    }
  }

  // to produce a new background fish
  public Fish addNewFish(int n) {
    return new Fish(new Posn(0, this.center.y + this.randomInt(n)), this.randomInt(n), this.color);
  }

  // helper method to generate a random number in the range -n to n
  public int randomInt(int n) {
    return -n + (new Random().nextInt(2 * n + 1));
  }

  // to produce a new fish with moving 5 pixels in background
  public Fish moveFish() {
    return new Fish(new Posn(this.center.x + 5, this.center.y), this.radius, this.color);
  }

  // to produce a new fish if it reaches the bound
  public Fish reachBound(int width, int height) {
    if (this.center.x == width) {
      return new Fish(new Posn(0, this.center.y), this.radius, this.color);
    }
    else {
      return this;
    }
  }

  // THE METHODS FOR ENDING THE GAME
  //
  public boolean isNearAnother(Fish that) {
    return this.center.x > that.center.x / 2 - 10 && this.center.x < that.center.x / 2 + 10
        && this.center.y > that.center.y / 2 - 10 && this.center.y < that.center.x / 2 + 10;
  }

}