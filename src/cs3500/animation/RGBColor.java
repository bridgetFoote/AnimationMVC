package cs3500.animation;

/**
 * Class to represent the RGB color of a shape in an animation.
 * Red, green, and blue gradient values are integers in range 0
 * to 255, inclusive.
 */
public class RGBColor {

  /**
   * Constructs a color with the given RGB gradient values.
   *
   * @param redGradient is the value for the red gradient.
   * @param greenGradient is the value for the green gradient.
   * @param blueGradient is the value for the blue gradient.
   * @throws IllegalArgumentException if any of the gradient values
   *                                  are out of range.
   */
  public RGBColor(int redGradient, int greenGradient, int blueGradient) {
    if (redGradient < 0 || redGradient > 255) {
      throw new IllegalArgumentException("The red gradient value is not valid.");
    }
    this.redGradient = redGradient;
    if (greenGradient < 0 || greenGradient > 255) {
      throw new IllegalArgumentException("The green gradient value is not valid.");
    }
    this.greenGradient = greenGradient;
    if (blueGradient < 0 || blueGradient > 255) {
      throw new IllegalArgumentException("The blue gradient value is not valid.");
    }
    this.blueGradient = blueGradient;
  }

  private int redGradient;
  private int greenGradient;
  private int blueGradient;

  /**
   * Changes the value of the given gradient type to the given new value
   * if it is different than the current value.
   * @param gradientType is the type of gradient to change.
   * @param newValue is the new gradient value.
   * @throws IllegalArgumentException if the gradient type is not valid
   *                                  or the new gradient value is out of range.
   */
  private void changeGradientValue(String gradientType, int newValue) {
    if (newValue < 0 || newValue > 255) {
      throw new IllegalArgumentException("The given gradient value is not valid.");
    }

    if (gradientType.equals("red") && (this.redGradient != newValue)) {
      this.redGradient = newValue;
    } else if (gradientType.equals("green") && (this.greenGradient != newValue)) {
      this.greenGradient = newValue;
    } else if (gradientType.equals("blue") && (this.blueGradient != newValue)) {
      this.blueGradient = newValue;
    } else {
      throw new IllegalArgumentException("The given gradient is not valid.");
    }
  }

  /**
   * Returns the value for the indicated color gradient.
   *
   * @param gradientType is the gradient value to return.
   * @return the integer value for the requested gradient type.
   * @throws IllegalArgumentException if the gradient type is not valid.
   */
  public int getColorGradient(String gradientType) {
    switch (gradientType) {
      case "red":
        return this.redGradient;
      case "green":
        return this.greenGradient;
      case "blue":
        return this.blueGradient;
      default:
        throw new IllegalArgumentException("The given gradient type is not valid.");
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof RGBColor)) {
      return false;
    }

    RGBColor color = (RGBColor) other;
    return (this.redGradient == color.redGradient)
            && (this.greenGradient == color.greenGradient)
            && (this.blueGradient == color.blueGradient);
  }

  @Override
  public int hashCode() {
    return (redGradient * 1000000) + (greenGradient * 1000) + (blueGradient);
  }
}
