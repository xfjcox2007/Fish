import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;

class ExamplesFishy {
  // examples of player fish
  Fish playerFish = new Fish(new Posn(100, 100), 30, Color.red);
  Fish fishleft = new Fish(new Posn(95, 100), 50, Color.green);
  Fish fishright = new Fish(new Posn(105, 100), 50, Color.yellow);
  Fish fishup = new Fish(new Posn(100, 95), 50, Color.red);
  Fish fishdown = new Fish(new Posn(100, 105), 50, Color.magenta);

  // examples of a background fish
  Fish fish1 = new Fish(new Posn(10, 35), 35, Color.BLUE);
  Fish fish2 = new Fish(new Posn(100, 100), 25, Color.BLUE);
  Fish fish3 = new Fish(new Posn(50, 200), 15, Color.BLUE);
  Fish fish4 = new Fish(new Posn(150, 100), 40, Color.BLUE);
  Fish fish5 = new Fish(new Posn(300, 150), 10, Color.BLUE);
  Fish fish6 = new Fish(new Posn(400, 245), 5, Color.BLUE);
  Fish fish7 = new Fish(new Posn(235, 175), 55, Color.BLUE);

  // examples of the background fishes
  ILoFish emptyCase = new MtLoFish();
  ILoFish lofish1 = new ConsLoFish(this.fish1, this.emptyCase);
  ILoFish lofish2 = new ConsLoFish(this.fish2, this.lofish1);
  ILoFish lofish3 = new ConsLoFish(this.fish3, this.lofish2);
  ILoFish lofish4 = new ConsLoFish(this.fish4, this.lofish3);
  ILoFish lofish5 = new ConsLoFish(this.fish5, this.lofish4);
  ILoFish lofish6 = new ConsLoFish(this.fish6, this.lofish5);
  ILoFish lofish7 = new ConsLoFish(this.fish7, this.lofish6);

  // examples of the world
  FishyWorld world1 = new FishyWorld(this.playerFish, this.emptyCase);
  FishyWorld world2 = new FishyWorld(this.playerFish, this.lofish1);
  FishyWorld world3 = new FishyWorld(this.playerFish, this.lofish4);
  FishyWorld world4 = new FishyWorld(this.playerFish, this.lofish5);
  FishyWorld world5 = new FishyWorld(this.fishleft, this.emptyCase);
  FishyWorld world6 = new FishyWorld(this.fishright, this.emptyCase);
  FishyWorld world7 = new FishyWorld(this.fishup, this.emptyCase);
  FishyWorld world8 = new FishyWorld(this.fishdown, this.emptyCase);

  // testing the moveFish method
  boolean testMoveFish(Tester t) {
    return t.checkExpect(this.playerFish.moveFish("left"), this.fishleft,
        "test moveFish - left " + "\n")
        && t.checkExpect(this.playerFish.moveFish("right"), this.fishright,
            "test movelob - right " + "\n")
        && t.checkExpect(this.playerFish.moveFish("up"), this.fishup, "test moveBlob - up " + "\n")
        && t.checkExpect(this.playerFish.moveFish("down"), this.fishdown,
            "test moveBlob - down " + "\n");
  }

  // testing the onKeyEvent method
  boolean testOnKeyEvent(Tester t) {
    return t.checkExpect(this.world1.onKeyEvent("left"), this.world5,
        "test moveBolb - left " + "\n")
        && t.checkExpect(this.world1.onKeyEvent("right"), this.world6,
            "test movelob - right " + "\n")
        && t.checkExpect(this.world1.onKeyEvent("up"), this.world7, "test moveBlob - up " + "\n")
        && t.checkExpect(this.world1.onKeyEvent("down"), this.world8,
            "test moveBlob - down " + "\n");
  }

  static Fish ff1 = new Fish(new Posn(100, 100), 10, Color.RED);
  static Fish ff2 = new Fish(new Posn(50, 50), 40, Color.GREEN);
  static Fish ff3 = new Fish(new Posn(25, 25), 30, Color.RED);
  static ILoFish mt = new MtLoFish();
  static ILoFish f1 = new ConsLoFish(ff1, new ConsLoFish(ff2, new ConsLoFish(ff3, mt)));
  

  // test that we can run three different animations concurrently
  // with the events directed to the correct version of the world
  /*
   * boolean runAnimation = this.w1.bigBang(200, 300, 0.3); boolean
   * runAnimation2 = this.w2.bigBang(200, 300, 0.3); boolean runAnimation3 =
   * this.w3.bigBang(200, 300, 0.3); (/
   * 
   * /** main: an alternative way of starting the world and running the tests
   */
  boolean runAnimation = this.world4.bigBang(600, 500, 0.3);

  public static void main(String[] argv) {

    // run the tests - showing only the failed test results
    //ExamplesFishy be = new ExamplesFishy();

    // run the game
   // FishyWorld w = new FishyWorld(new Fish(new Posn(150, 100), 20, Color.BLUE), f1);
    //w.bigBang(200, 300, 0.3);

    /*
     * Canvas c = new Canvas(200, 300); c.show();
     * System.out.println(" let's see: \n\n" +
     * Printer.produceString(w.makeImage())); c.drawImage(new OverlayImages(new
     * CircleImage(new Posn(50, 50), 20, Color.RED), new RectangleImage(new
     * Posn(20, 30), 40, 20, Color.BLUE)));
     */

  }

}

