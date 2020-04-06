package cs3500.animation.model;

/**
 * Represents all of the information needed to draw a shape. This will be used by the visual view
 * to quickly and easily draw the shape at each tick.
 */
public final class ShapeDrawParam {
  public ShapeType type;
  public int xPosn;
  public int yPosn;
  public int width;
  public int height;
  public RGBColor color;

  /**
   * Create an instance of everything that the the visual view needs to draw a shape.
   * @param type the type of shape to draw
   * @param x the x position
   * @param y the y position
   * @param w the width
   * @param h the height
   * @param c the color
   */
  public ShapeDrawParam(ShapeType type, int x, int y, int w, int h, RGBColor c) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Width and height must be greater than zero.");
    }
    this.type = type;
    this.xPosn = x;
    this.yPosn = y;
    this.width = w;
    this.height = h;
    this.color = c;
  }

}
