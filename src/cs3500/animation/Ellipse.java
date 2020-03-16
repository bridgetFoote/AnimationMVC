package cs3500.animation;

/**
 * Represents the ellipse shape, has two dimensions fields, one for
 * length and the other for width.
 */
public class Ellipse extends SimpleShape {

  /**
   * Constructor for an ellipse.
   *
   * @param rGBColor      is the color of the shape.
   * @param width is the width for this ellipse.
   * @param height is the height for this ellipse.
   * @param name is the name of this shape.
   */
  public Ellipse(RGBColor rGBColor, double width, double height, String name) {
    super(rGBColor, width, height, name);
  }

  @Override
  SimpleShape returnCopy() {
    return new Ellipse(new RGBColor(this.getColorGradient("red"),
            this.getColorGradient("green"), this.getColorGradient("blue")),
            this.getShapeWidth(), this.getShapeHeight(), this.getName());
  }


  @Override
  void setShapeType() {
    this.shapeType = "ellipse";
  }
}
