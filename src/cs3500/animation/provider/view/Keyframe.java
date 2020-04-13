package cs3500.animation.provider.view;

import cs3500.animation.model.KeyFrame;

/**
 * Represents our provider's implementation of a keyframe.
 */
public class Keyframe extends KeyFrame implements IMotion {

  /**
   * Public contructor for a keyframe object, sets the parameter values.
   *
   * @param tick is the tick of this keyframe.
   * @param xCoord is the x coordinate of the shape in this keyframe.
   * @param yCoord is the y coordinate of the shape in this keyframe.
   * @param width is the width of the shape in this keyframe.
   * @param height is the height of the shape in this keyframe.
   * @param redGradient is the red color value of the shape in this keyframe.
   * @param greenGradient is the green color value of the shape in this keyframe.
   * @param blueGradient is the blue color value of the shape in this keyframe.
   */
  public Keyframe(int tick, int xCoord, int yCoord, int width, int height, int redGradient, int greenGradient, int blueGradient) {
    super(tick, xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
  }

  public Keyframe(KeyFrame kf) {
    super(kf);
  }

  /**
   * Returns the tick of this key frame.
   *
   * @return the integer time this keyframe occurs.
   */
  protected int getTime() {
    return this.getStartTick();
  }

  @Override
  public String toString() {
    return "Time: " + Integer.toString(this.getTime()) + " Posn: "
            + Double.toString((double) this.getCoord("x", ""))
            + " " + Double.toString((double) this.getCoord("y", ""))
            + " Width: " + Double.toString((double) this.getWidth(""))
            + " Height: " + Double.toString((double) this.getHeight(""))
            + " Red: " + Integer.toString(this.getColor("").getColorGradient("red"))
            + " Green: " + Integer.toString(this.getColor("").getColorGradient("green"))
            + " Blue: " + Integer.toString(this.getColor("").getColorGradient("blue"));
  }
}
