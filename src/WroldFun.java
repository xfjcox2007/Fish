import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;

//represent the world of the fishy
class FishyWorld extends World {
  int width = 600;
  int height = 500;
  Fish playerfish;
  ILoFish backgroundfishes;

  // constructor
  public FishyWorld(Fish playerfish, ILoFish backgroundfishes) {
    super();
    this.playerfish = playerfish;
    this.backgroundfishes = backgroundfishes;
  }

  // move the player fish when the player presses a key
  public World onKeyEvent(String key) {
    return new FishyWorld(this.playerfish.moveFish(key), this.backgroundfishes);
  }

  // on time move the background fishes in 5 pixels
  public World onTick() {
   return new FishyWorld(this.backgroundfishes.growUpHelp(this.playerfish).reEnter(width, height),
        this.backgroundfishes.reachBoundILoFish(this.width, this.height).moveILoFish()
            .removeFish(this.playerfish));
  }

  // produce the image of this world by adding the background fish and player
  // fish
  public WorldScene makeScene() {
    WorldScene background = this.getEmptyScene();
    WorldScene backgroundbg = this.backgroundfishes.backgroundFishImage(background);
    WorldScene backgroundpl = backgroundbg.placeImageXY(this.playerfish.fishImage(), this.playerfish.center.x, this.playerfish.center.y);
//    WorldScene background = this.getEmptyScene().placeImageXY(this.playerfish.fishImage(), this.playerfish.center.x, this.playerfish.center.y);
//    WorldScene backgroundbg = this.backgroundfishes.backgroundFishImage(background);
//    WorldScene scene = this.playerfish.fishImage().
    return backgroundpl;
 
  }

  // to check when should end the game,
  // - eat a bigger fish
  // - or all the background fished are eaten

  public WorldEnd worldEnds() {
    // the first situation
    if (this.backgroundfishes.isNearAndBigger(this.playerfish)) {
      return new WorldEnd(true, this.lastScene());
    }
    // the second situation
    if (this.backgroundfishes.isBiggestOne(this.playerfish)) {
      return new WorldEnd(true, this.lastScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

 
  public WorldScene lastScene() {
    return this.makeScene().placeImageXY(new TextImage("Game Over", Color.red), 300, 40);
  }

  // support for the regression tests
  public static ExamplesFishy examplesInstance = new ExamplesFishy();

}