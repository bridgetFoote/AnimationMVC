package cs3500.animation.provider.view;

import cs3500.animation.model.RGBColor;
import cs3500.animation.model.Shape;
import cs3500.animation.model.ShapeType;

public class Ellipse extends Shape {

  /**
   * Creates a new ellipse shape with the given name.
   *
   * @param name is the name of the ellipse.
   */
  public Ellipse(String name) {
    super(name, ShapeType.ELLIPSE);
  }
}
