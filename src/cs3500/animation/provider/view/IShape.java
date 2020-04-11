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

  /**
   * Returns the shape's stored position.
   *
   * @return the Posn representing the shape's position.
   */
  Posn getPosn();

  /**
   * Returns the width of this shape.
   *
   * @return the integer width of this shape.
   */
  int getWidth();

  /**
   * Returns the height of this shape.
   *
   * @return the integer height of this shape.
   */
  int getHeight();

  /**
   * Sets this shape's position to its instantaneous position at the given tick.
   *
   * @param tick is the tick to set the position at.
   * @throws IllegalArgumentException if the shape does not appear in the animation
   *                                  at the given tick.
   */
  void setShapePosition(int tick);

  /**
   * Sets this shape's width to its instantaneous width at the given tick.
   *
   * @param tick is the tick to set the width at.
   * @throws IllegalArgumentException if the shape does not appear in the animation
   *                                  at the given tick.
   */
  void setShapeWidth(int tick);

  /**
   * Sets this shape's height to its instantaneous height at the given tick.
   *
   * @param tick is the tick to set the height at.
   * @throws IllegalArgumentException if the shape does not appear in the animation
   *                                  at the given tick.
   */
  void setShapeHeight(int tick);

  /**
   * Sets this shape's color to its instantaneous color at the given tick.
   *
   * @param tick is the tick to set the color at.
   * @throws IllegalArgumentException if the shape does not appear in the animation
   *                                  at the given tick.
   */
  void setShapeColor(int tick);
}
