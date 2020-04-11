package cs3500.animation.provider.view;

import cs3500.animation.model.KeyFrame;
import cs3500.animation.model.RGBColor;
import cs3500.animation.model.ShapeType;
import cs3500.animation.model.ShapeWithKeyFrames;

import java.util.List;

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

  public Shape() {
    super("", null);
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public Posn getPosn() {
    return this.position;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setShapePosition(int tick) {
    if (tick < this.keyFrames.firstKey()) {
      throw new IllegalArgumentException("The shape does not exist at this tick.");
    }
    List<Integer> position = this.getPosition(tick);
    this.position = new Posn(position.get(0), position.get(1));
  }

  @Override
  public void setShapeWidth(int tick) {
    if (tick < this.keyFrames.firstKey()) {
      throw new IllegalArgumentException("The shape does not exist at this tick.");
    }
    KeyFrame before = this.keyFrames.floorEntry(tick).getValue();
    KeyFrame after = this.keyFrames.ceilingEntry(tick).getValue();
    this.width = linearInterpolation(before.getStartTick(),
            after.getStartTick(), tick, before.getWidth(""), after.getWidth(""));
  }

  @Override
  public void setShapeHeight(int tick) {
    if (tick < this.keyFrames.firstKey()) {
      throw new IllegalArgumentException("The shape does not exist at this tick.");
    }
    KeyFrame before = this.keyFrames.floorEntry(tick).getValue();
    KeyFrame after = this.keyFrames.ceilingEntry(tick).getValue();
    this.width = linearInterpolation(before.getStartTick(),
            after.getStartTick(), tick, before.getHeight(""), after.getHeight(""));
  }

  @Override
  public void setShapeColor(int tick) {
    if (tick < this.keyFrames.firstKey()) {
      throw new IllegalArgumentException("The shape does not exist at this tick.");
    }
    KeyFrame before = this.keyFrames.floorEntry(tick).getValue();
    KeyFrame after = this.keyFrames.ceilingEntry(tick).getValue();
    int redGradient = linearInterpolation(before.getStartTick(),
            after.getStartTick(), tick, before.getColor("").getRed(),
            after.getColor("").getRed());
    int greenGradient = linearInterpolation(before.getStartTick(),
            after.getStartTick(), tick, before.getColor("").getGreen(),
            after.getColor("").getGreen());
    int blueGradient = linearInterpolation(before.getStartTick(),
            after.getStartTick(), tick, before.getColor("").getBlue(),
            after.getColor("").getBlue());
    this.color = new RGBColor(redGradient, greenGradient, blueGradient);
  }
}
