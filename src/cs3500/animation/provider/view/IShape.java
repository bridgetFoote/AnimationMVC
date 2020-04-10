package cs3500.animation.provider.view;

import cs3500.animation.model.IShapeWithKeyFrames;
import cs3500.animation.model.RGBColor;

/**
 * Alias for IShapeWithKeyFrames in this package.
 */
public interface IShape extends IShapeWithKeyFrames {

  /**
   * Returns the color of this shape.
   *
   * @return the RGBColor of this shape.
   */
  RGBColor getColor();
}
