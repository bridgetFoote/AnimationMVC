package cs3500.animation.provider.view;

import cs3500.animation.model.Posn;

import java.util.List;

/**
 * Interface representing our provider's implementation of a shape.
 */
public interface IShape {

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape as an IColor object.
   */
  IColor getColor();

  /**
   * Returns a list of the key frames representing the motions of this shape.
   *
   * @return the list of key frames, empty list if this shape has no frames.
   */
  List<Keyframe> getKeyframes();

  /**
   * Returns the position of this shape.
   *
   * @return the position of this shape as a Posn object.
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
   * Sorts the actions stored in this shape in chronological order
   * according to start tick.
   */
  void sortActions();
}
