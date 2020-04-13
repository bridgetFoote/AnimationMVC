package cs3500.animation.provider.view;

import cs3500.animation.model.Posn;
import cs3500.animation.model.SWKFToIShapeAdapter;
import cs3500.animation.model.ShapeType;

/**
 * Represents our provider's implementation of a Rectangle.
 */
public class Rectangle extends SWKFToIShapeAdapter implements IShape {
  /**
   * Public contsructor, sets all params.
   *
   * @param name is the name of the shape.
   * @param type is the type of the shape.
   * @param p    is the position of the shape.
   * @param w    is the width of the shape.
   * @param h    is the height of the shape.
   * @param c    is the color of the shape.
   */
  public Rectangle(String name, ShapeType type, Posn p, double w, double h, IColor c) {
    super(name, type, p, w, h, c);
  }
}
