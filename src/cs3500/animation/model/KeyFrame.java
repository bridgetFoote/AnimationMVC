package cs3500.animation.model;

/**
 * Represents a key frame in the animation for a shape,
 * can be thought of as a freeze frame of the animation at a
 * specific tick.
 */
public class KeyFrame implements IAction {

  private int tick;
  private int xCoord;
  private int yCoord;
  private int width;
  private int height;
  private int redGradient;
  private int greenGradient;
  private int blueGradient;

  /**
   * Creates a new keyframe with the given parameters.
   *
   * @param tick is the tick of this key frame.
   * @param xCoord is the x coordinate of the shape.
   * @param yCoord is the y coordinate of the shape.
   * @param width is the width of the shape.
   * @param height is the height of the shape.
   * @param redGradient is the red value for the shape's color.
   * @param greenGradient is the green value for the shape's color.
   * @param blueGradient is the blue value for the  shape's color.
   */
  public KeyFrame(int tick, int xCoord, int yCoord, int width, int height, int redGradient, int greenGradient, int blueGradient) {
    if ((xCoord < 0) || (yCoord < 0) || (width <= 0) || (height <= 0)
            || (redGradient < 0) || (redGradient > 255) || (greenGradient < 0)
            || (greenGradient > 255) || (blueGradient < 0) || (blueGradient > 255)) {
      throw new IllegalArgumentException("The inputs do not describe a valid keyframe.");
    }
    this.tick = tick;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.width = width;
    this.height = height;
    this.redGradient = redGradient;
    this.greenGradient = greenGradient;
    this.blueGradient = blueGradient;
  }

  public KeyFrame(KeyFrame kf) {
    this.xCoord = kf.getCoord("x", "");
    this.yCoord = kf.getCoord("y", "");
    this.width = kf.getWidth();
    this.height = kf.getHeight();
    this.redGradient = kf.getColorGradient("red");
    this.greenGradient = kf.getColorGradient("green");
    this.blueGradient = kf.getColorGradient("blue");
  }

  /**
   * Returns the height of the shape in this key frame.
   *
   * @return the integer value for the height of the shape.
   */
  private int getHeight() {
    return this.height;
  }

  /**
   * Returns the color gradient value indicated for the shape in this key frame.
   *
   * @param whichColor is which gradient to return.
   * @return the integer value of the indicated color gradient.
   * @throws IllegalArgumentException if the gradient type is not red, green, or blue.
   */
  private int getColorGradient(String whichColor) {
    switch (whichColor) {
      case "red":
        return this.redGradient;
      case "green":
        return this.greenGradient;
      case "blue":
        return this.blueGradient;
      default:
        throw new IllegalArgumentException("Gradient type not valid.");
    }
  }

  /**
   * Returns the width of the shape in this key frame.
   *
   * @return the integer value of the width of the shape in this key frame.
   */
  private int getWidth() {
    return this.width;
  }

  @Override
  public IAction returnCopy() {
    return new KeyFrame(this);
  }

  @Override
  public int getStartTick() {
    return this.tick;
  }

  @Override
  public int getCoord(String xOrY, String startOrEnd) {
    if (xOrY.equals("x")) {
      return this.xCoord;
    } else if (xOrY.equals("y")) {
      return this.yCoord;
    } else {
      throw new IllegalArgumentException("The given x or y indicator is invalid.");
    }
  }

  @Override
  public int getEndTick() {
    return this.tick;
  }

  @Override
  public ActionType getActionType() {
    return ActionType.KEYFRAME;
  }

  @Override
  public boolean hasOverlap(IAction other) {
    return this.tick == (other.getStartTick());
  }

  @Override
  public boolean causesTeleportation(IAction other) {
    return false;
  }

  @Override
  public String toString(IShape shape) {
    return null;
  }
}
