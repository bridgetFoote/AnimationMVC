package cs3500.animation;

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
    this.shapeType = ShapeType.RECTANGLE;
  }

  @Override
  public IShape returnCopy() {
    return new Rectangle(this.getName());
  }
}
