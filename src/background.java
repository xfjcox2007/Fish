import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;
import java.util.Random;


// an interface that represents a list of fishes
// which is empty, or non-empty list
interface ILoFish {

  // to produce a world image of the background fishes
  public WorldScene backgroundFishImage(WorldScene background);

  // ------------------- ON TICK -------------------
  // to produce a new list with all fishes moved 5 pixels
  public ILoFish moveILoFish();

  // to produce a new list for the fish which reached the bound
  public ILoFish reachBoundILoFish(int width, int height);

  // to produce a bigger fish when it eats a smaller fish
  public Fish growUpHelp(Fish player);

  // is near to a fish with a bigger radius than the given fish
  public boolean isNearAndBigger(Fish player);

  // to remove a background fish when the player fish is near and bigger
  public ILoFish removeFish(Fish player);

  // is the player fish the biggest fish
  public boolean isBiggestOne(Fish player);
}

// a class that represents an empty list of fishes
class MtLoFish implements ILoFish {

  // ------------------- ON TICK -------------------
  // to produce a new list with all fishes moved 5 pixels
  public ILoFish moveILoFish() {
    return this;
  }

  public ILoFish reachBoundILoFish(int width, int height) {
    return this;
  }

  // to produce a world image of the background fishes
  public WorldScene backgroundFishImage(WorldScene background) {
    return background;
  }

  // to produce a bigger fish when it eats a smaller fish
  public Fish growUpHelp(Fish player) {
    return player;
  }

  // is near to a fish with a bigger radius than the given fish
  public boolean isNearAndBigger(Fish player) {
    return false;
  }

  // to remove a background fish when the player fish is near and bigger
  public ILoFish removeFish(Fish player) {
    return this;
  }

  // is the player fish the biggest fish
  public boolean isBiggestOne(Fish player) {
    return true;
  }
}

// a class that represents a non-empty list
class ConsLoFish implements ILoFish {
  Fish first;
  ILoFish rest;

  // constructor
  ConsLoFish(Fish first, ILoFish rest) {
    this.first = first;
    this.rest = rest;
  }

  // ------------------- ON TICK -------------------
  // to produce a new list with all fishes moved 5 pixels
  public ILoFish moveILoFish() {
    return new ConsLoFish(this.first.moveFish(), this.rest.moveILoFish());
  }

  public ILoFish reachBoundILoFish(int width, int height) {
    return new ConsLoFish(this.first.reachBound(width, height),
        this.rest.reachBoundILoFish(width, height));
  }

  // to produce a bigger fish when it eats a smaller fish
  public Fish growUpHelp(Fish player) {
    if (this.first.isNearAnother(player) && this.first.radius < player.radius) {
      return new Fish(player.center, player.radius + 5, player.color);
    }
    else {
      return player;
    }

  }

  // is near to a fish with a bigger radius than the given fish
  public boolean isNearAndBigger(Fish player) {
    return (this.first.isNearAnother(player) && this.first.radius > player.radius)
        || this.rest.isNearAndBigger(player);
  }

  // to remove a background fish when the player fish is near and bigger
  public ILoFish removeFish(Fish player) {
    if (this.first.isNearAnother(player) && this.first.radius < player.radius) {
      return this.rest;
    }
    else {
      return new ConsLoFish(this.first, this.rest.removeFish(player));
    }
  }

  // is the player fish the biggest fish
  public boolean isBiggestOne(Fish player) {
    return this.first.radius < player.radius && this.rest.isBiggestOne(player);
  }

  // to produce a world image of the background fishes
  public WorldScene backgroundFishImage(WorldScene background) {
    return this.rest.backgroundFishImage(
        background.placeImageXY(this.first.fishImage(), this.first.center.x, this.first.center.y));
  }

}