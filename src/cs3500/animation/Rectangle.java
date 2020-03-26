package cs3500.animation;

import java.util.Objects;

/**
 * Represents the rectangle shape, has two fields for dimensions, one for
 * length and one for width.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for a rectangle shape.
   *
   * @param name is the name of this shape.
   */
  public Rectangle(String name) {
    super(name, ShapeType.RECTANGLE);
  }


  @Override
  public int hashCode() {
    return Objects.hash(this.getName());
  }

  @Override
  public boolean equals(Object that) {
    if (that instanceof Rectangle) {
      return this.getName().equals(((Rectangle) that).getName());
    }
    return false;
  }
}
