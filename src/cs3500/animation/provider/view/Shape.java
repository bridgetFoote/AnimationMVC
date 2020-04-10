package cs3500.animation.provider.view;

import cs3500.animation.model.RGBColor;
import cs3500.animation.model.ShapeType;
import cs3500.animation.model.ShapeWithKeyFrames;

public class Shape extends ShapeWithKeyFrames implements IShape {

  private IColor color;
  private int width;
  private int height;
  private Posn position;

  public Shape(String name, ShapeType type, IColor color, int width, int height, Posn position) {
    super(name, type);
    if ((this.width <= 0) || (this.height <= 0)) {
      throw new IllegalArgumentException("Width and height must be greater than 0.");
    }
    this.color = color;
    this.width = width;
    this.height = height;
    this.position = position;
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}
