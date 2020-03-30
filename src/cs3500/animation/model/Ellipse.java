package cs3500.animation.model;

/**
 * Represents the ellipse shape, has two dimensions fields, one for
 * length and the other for width.
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructor for an ellipse.
   *
   * @param name is the name of this shape.
   */
  public Ellipse(String name) {
    super(name, ShapeType.ELLIPSE);
  }


}
