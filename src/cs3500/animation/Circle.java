package cs3500.animation;

/**
 * Represents the circle shape, has a single dimensions field for the radius.
 */
public class Circle extends SimpleShape {

  /**
   * Constructor for a circle.
   *
   * @param rGBColor is the color of the shape.
   * @param width is the radius for this circle.
   * @param height is the radius for this circle.
   * @param name is the name of this shape.
   */
  public Circle(RGBColor rGBColor, double width, double height, String name) {
    super(rGBColor, width, height, name);
  }

  @Override
  SimpleShape returnCopy() {
    return new Circle(new RGBColor(this.getColorGradient("red"),
            this.getColorGradient("green"), this.getColorGradient("blue")),
            this.getShapeWidth(), this.getShapeHeight(), this.getName());
  }

  @Override
  void setShapeType() {
    this.shapeType = "circle";
  }
}
