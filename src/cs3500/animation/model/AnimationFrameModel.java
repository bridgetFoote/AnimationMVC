package cs3500.animation.model;

import java.util.*;

/**
 * Represents an animation where the motions of the shapes are represented by
 * keyframes where the shape's characteristics at any given tick are found
 * by tweening.
 */
public class AnimationFrameModel extends AnimationModel implements AnimationFrameOperations {

  /**
   * Creates a new animation using keyframes to keep track of shape movement.
   */
  public AnimationFrameModel() {
    super();
  }

  @Override
  public void orderByTick() {
    int[] ticksActive;
    for (IShape s: shapes.values()) {
      ticksActive = this.findTicks(s);
      for (Integer t: ticksActive) {
        ShapeDrawParam newShape = this.findShapeParams(s,t);
        if (orderedShapes.containsKey(t)) {
          List<ShapeDrawParam> cShapes = orderedShapes.get(t);
          cShapes.add(newShape);
          orderedShapes.put(t,cShapes);
        }
        else {
          List<ShapeDrawParam> nShapes = new ArrayList<>();
          nShapes.add(newShape);
          orderedShapes.put(t,nShapes);
        }
      }
    }
  }

  @Override
  protected ShapeDrawParam findShapeParams(IShape s, Integer tick) {
    if (!(s instanceof IShapeWithKeyFrames)) {
      throw new IllegalStateException();
    }
    IShapeWithKeyFrames shape = (ShapeWithKeyFrames) s;
    if (shape.hasFrameAt(tick)) {
      KeyFrame frame = shape.getFrame(tick);
      return new ShapeDrawParam(shape.getName(), shape.getType(),
              frame.getCoord("x", ""),
              frame.getCoord("y", ""),
              frame.getWidth(""), frame.getHeight(""),
              frame.getColor("start"));
    } else if (tick > shape.getLastTick()) {
      KeyFrame frame = shape.getFrame(shape.getLastTick());
      return new ShapeDrawParam(shape.getName(), shape.getType(),
              frame.getCoord("x", ""),
              frame.getCoord("y", ""),
              frame.getWidth(""), frame.getHeight(""),
              frame.getColor("start"));
    } else {
      KeyFrame before = shape.getFrameBefore(tick);
      KeyFrame after = shape.getFrameAfter(tick);
      int newX = linearInterpolation(before.getStartTick(), after.getEndTick(), tick, before.getCoord("x", "start"),
              after.getCoord("x", "end"));
      int newY = linearInterpolation(before.getStartTick(), after.getEndTick(), tick, before.getCoord("y", "start"),
              after.getCoord("y", "end"));
      int newWidth = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getWidth("start"), after.getWidth("end"));
      int newHeight = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getHeight("start"), after.getHeight("end"));
      int newR = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getColor("start").getColorGradient("red"),
              after.getColor("end").getColorGradient("red"));
      int newG = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getColor("start").getColorGradient("green"),
              after.getColor("end").getColorGradient("green"));
      int newB = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getColor("start").getColorGradient("blue"),
              after.getColor("end").getColorGradient("blue"));
      return new ShapeDrawParam(s.getName(), s.getType(), newX, newY, newWidth, newHeight,
              new RGBColor(newR, newG, newB));
    }
  }

  @Override
  protected int[] findTicks(IShape shape) {
    if (!(shape instanceof IShapeWithKeyFrames)) {
      throw new IllegalStateException();
    }
    IShapeWithKeyFrames s = (ShapeWithKeyFrames) shape;
    int firstTick = s.getFirstTick();
    int lastTick = s.getLastTick();
    int[] outArr = new int[lastTick - firstTick];
    int j = 0;
    for (int i = firstTick; i < lastTick; i++) {
      outArr[j] = i;
      j++;
    }
    return outArr;
  }

  @Override
  public void addShape(String name, String shapeType) {
    ShapeType type;
    if (ShapeType.RECTANGLE.str.equals(shapeType)) {
      type = ShapeType.RECTANGLE;
    } else if (ShapeType.ELLIPSE.str.equals(shapeType)) {
      type = ShapeType.ELLIPSE;
    } else {
      throw new IllegalArgumentException("Invalid shape type");
    }
    IShapeWithKeyFrames shape = new ShapeWithKeyFrames(name, type);

    if (this.checkForDuplicateShapeNames(shape.getName())) {
      throw new IllegalArgumentException("A shape with this name already "
              + "exists in this animation.");
    }
    this.shapes.put(shape.getName(), shape);
  }

  @Override
  public void addKeyFrame(String name, int tick, int xCoord,
                          int yCoord, int width, int height, int redGradient,
                          int greenGradient, int blueGradient) {
    if (this.shapes.containsKey(name)) {
      if (!(this.shapes.get(name) instanceof IShapeWithKeyFrames)) {
        throw new IllegalStateException();
      }
      IShapeWithKeyFrames shape = (ShapeWithKeyFrames) this.shapes.get(name);
      shape.addKeyFrame(new KeyFrame(tick, xCoord, yCoord,
              width, height, redGradient, greenGradient, blueGradient));
    } else {
      throw new IllegalArgumentException("Shape doesn't exist in the model");
    }
  }

  @Override
  public void removeKeyFrame(String name, int tick) {
    if (this.shapes.containsKey(name)) {
      if (!(this.shapes.get(name) instanceof IShapeWithKeyFrames)) {
        throw new IllegalStateException();
      }
      IShapeWithKeyFrames shape = (ShapeWithKeyFrames) this.shapes.get(name);
      shape.removeKeyFrame(tick);
    } else {
      throw new IllegalArgumentException("Invalid inputs.");
    }
  }

  @Override
  public void removeShape(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with this name does not exist in the animation.");
    } else {
      this.shapes.remove(name);
    }
  }

  @Override
  public void editKeyFrame(String name, int tick, int xCoord,
                           int yCoord, int width, int height,
                           int redGradient, int greenGradient, int blueGradient) {
    if (this.shapes.containsKey(name)) {
      if (!(this.shapes.get(name) instanceof IShapeWithKeyFrames)) {
        throw new IllegalStateException();
      }
      IShapeWithKeyFrames shape = (ShapeWithKeyFrames) this.shapes.get(name);
      if (shape.hasFrameAt(tick)) {
        shape.editKeyFrame(tick, xCoord, yCoord, width, height,
                redGradient, greenGradient, blueGradient);
      }
      else {
        throw new IllegalArgumentException("Shape has no frame at this tick");
      }
    }
    else {
      throw new IllegalArgumentException("Shape doesn't exist in the animation.");
    }
  }

  @Override
  public ShapeWithKeyFrames getShapeWithName(String name) {
    if (! (shapes.containsKey(name))) {
      throw new IllegalArgumentException("This shape doesn't exist");
    }
    return (ShapeWithKeyFrames) this.shapes.get(name);
  }



  /**
   * Builds an Animation.
   */
  public static final class Builder implements AnimationBuilder<AnimationFrameOperations> {
    private AnimationFrameOperations model = new AnimationFrameModel();

    @Override
    public AnimationFrameOperations build() {
      this.model.orderByTick();
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationFrameOperations> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationFrameOperations> declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationFrameOperations> addMotion(String name, int t1, int x1, int y1,
                                                                int w1, int h1, int r1,
                                                                int g1, int b1, int t2,
                                                                int x2, int y2, int w2, int h2,
                                                                int r2, int g2, int b2) {
      this.model.addShapeAction(name, t1, t2, x1, y1, x2, y2 ,r1, g1, b1, r2,
              g2, b2, w1, h1, w2,h2);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationFrameOperations> addKeyframe(String name, int t, int x, int y,
                                                                  int w, int h, int r, int g, int b) {
      this.model.addKeyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}
