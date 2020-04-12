package cs3500.animation.model;

import cs3500.animation.provider.view.IColor;

public class RGBToIColorAdapter extends RGBColor implements IColor {
  /**
   * Constructs a color with the given RGB gradient values.
   *
   * @param redGradient   is the value for the red gradient.
   * @param greenGradient is the value for the green gradient.
   * @param blueGradient  is the value for the blue gradient.
   * @throws IllegalArgumentException if any of the gradient values
   *                                  are out of range.
   */
  public RGBToIColorAdapter(int redGradient, int greenGradient, int blueGradient) {
    super(redGradient, greenGradient, blueGradient);
  }
}
