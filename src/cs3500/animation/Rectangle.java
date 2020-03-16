package cs3500.animation;

/**
 * Represents the rectangle shape, has two fields for dimensions, one for
 * length and one for width.
 */
public class Rectangle extends SimpleShape {

  /**
   * Constructor for a rectangle shape.
   *
   * @param rGBColor is the color of the shape.
   * @param width is the width for this shape.
   * @param height is the height for this shape.
   * @param name is the name of this shape.
   */
  public Rectangle(RGBColor rGBColor, double width, double height, String name) {
    super(rGBColor, width, height, name);
  }

  @Override
  SimpleShape returnCopy() {
    return new Rectangle(new RGBColor(this.getColorGradient("red"),
            this.getColorGradient("green"), this.getColorGradient("blue")),
            this.getShapeWidth(), this.getShapeHeight(), this.getName());
  }


  @Override
  void setShapeType() {
    this.shapeType = "rectangle";
  }
}
