package cs3500.animation.provider.view;

/**
 * Interface representing a color of a shape.
 */
public interface IColor {

  /**
   * Returns the value for the indicated color gradient.
   *
   * @param gradientType is the gradient value to return.
   * @return the integer value for the requested gradient type.
   * @throws IllegalArgumentException if the gradient type is not valid.
   */
  int getColorGradient(String gradientType);

  /**
   * Returns the red gradient of this color.
   *
   * @return the red value for this color.
   */
  int getRed();

  /**
   * Returns the green gradient of this color.
   *
   * @return the green value for this color.
   */
  int getGreen();

  /**
   * Returns the blue gradient of this color.
   *
   * @return the blue value for this color.
   */
  int getBlue();

}
