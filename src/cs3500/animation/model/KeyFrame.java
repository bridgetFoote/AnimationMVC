package cs3500.animation.model;

import java.util.Objects;

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
  private RGBColor color;

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
  public KeyFrame(int tick, int xCoord, int yCoord, int width, int height, int redGradient,
                  int greenGradient, int blueGradient) {
    if ((width <= 0) || (height <= 0) || (tick < 0)
            || (redGradient < 0) || (redGradient > 255) || (greenGradient < 0)
            || (greenGradient > 255) || (blueGradient < 0) || (blueGradient > 255)) {
      throw new IllegalArgumentException("The inputs do not describe a valid keyframe.");
    }
    this.tick = tick;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.width = width;
    this.height = height;
    this.color = new RGBColor(redGradient, greenGradient, blueGradient);
  }

  public KeyFrame(KeyFrame kf) {
    this.xCoord = kf.getCoord("x", "");
    this.yCoord = kf.getCoord("y", "");
    this.width = kf.getWidth("");
    this.height = kf.getHeight("");
    this.color = kf.getColor("");
  }

  @Override
  public int getHeight(String startOrEnd) {
    return this.height;
  }

  @Override
  public RGBColor getColor(String whichColor) {
    return new RGBColor(this.color.getColorGradient("red"),
            this.color.getColorGradient("green"),
            this.color.getColorGradient("blue"));
  }

  @Override
  public int getWidth(String startOrEnd) {
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

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof KeyFrame)) {
      return false;
    }
    KeyFrame kf = (KeyFrame) other;
    return (this.tick == kf.tick) && (this.xCoord == kf.xCoord)
            && (this.yCoord == kf.yCoord) && (this.width == kf.width)
            && (this.height == kf.height) && (this.color.equals(((KeyFrame) other).color));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.tick, this.xCoord, this.yCoord, this.width, this.height, this.color);
  }

  /**
   * Edits this key frame with the given inputs.
   * @param xCoord is the new x coordinate or null.
   * @param yCoord is the new y coordinate or null.
   * @param width is the new width or null.
   * @param height is the new height or null.
   * @param redGradient is the new red gradient or null.
   * @param greenGradient is the new green gradient or null.
   * @param blueGradient is the new blue gradient or null.
   */
  public void edit(int xCoord, int yCoord, int width, int height,
                   int redGradient, int greenGradient, int blueGradient) {
    if (!Objects.isNull(xCoord)) {
      if (xCoord < 0) {
        throw new IllegalArgumentException("Invalid x coordinate.");
      } else {
        this.xCoord = xCoord;
      }
    }
    if (!Objects.isNull(yCoord)) {
      if (yCoord < 0) {
        throw new IllegalArgumentException("Invalid y coordinate.");
      } else {
        this.yCoord = yCoord;
      }
    }
    if (!Objects.isNull(width)) {
      if (width <= 0) {
        throw new IllegalArgumentException("Invalid width.");
      } else {
        this.width = width;
      }
    }
    if (!Objects.isNull(height)) {
      if (height <= 0) {
        throw new IllegalArgumentException("Invalid height.");
      } else {
        this.height = height;
      }
    }
    int red;
    int green;
    int blue;
    if (!Objects.isNull(redGradient)) {
      red = redGradient;
    } else {
      red = this.color.getColorGradient("red");
    }
    if (!Objects.isNull(greenGradient)) {
      green = greenGradient;
    } else {
      green = this.color.getColorGradient("green");
    }
    if (!Objects.isNull(blueGradient)) {
      blue = blueGradient;
    } else {
      blue = this.color.getColorGradient("blue");
    }
    this.color = new RGBColor(red, green, blue);
  }
}
