package cs3500.animation;

/**
 * Represents the square shape, has one dimensions field for side length.
 */
public class Square extends SimpleShape {

  /**
   * Constructor for a square.
   *
   * @param rGBColor      is the color of the shape.
   * @param width is the width for this square.
   * @param height is the height for this square.
   * @param name is the name of this shape.
   */
  public Square(RGBColor rGBColor, double width, double height, String name) {
    super(rGBColor, width, height, name);
  }

  @Override
  SimpleShape returnCopy() {
    return new Square(new RGBColor(this.getColorGradient("red"),
            this.getColorGradient("green"), this.getColorGradient("blue")),
            this.getShapeWidth(), this.getShapeHeight(), this.getName());
  }

  @Override
  void setShapeType() {
    this.shapeType = "square";
  }
}
