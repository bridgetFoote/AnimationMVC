package cs3500.animation.provider.view;

import cs3500.animation.model.RGBColor;
import cs3500.animation.model.Shape;
import cs3500.animation.model.ShapeType;

public class Rectangle extends Shape {

  /**
   * Creates a new rectangle shape with the given name.
   *
   * @param name is the name of the shape.
   */
  public Rectangle(String name) {
    super(name, ShapeType.RECTANGLE);
  }
}
