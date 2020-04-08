package cs3500.animation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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

  /*
  @Override
  public void orderByTick() {
    for (IShape shape : this.shapes.values()) {
      TreeMap<Integer, KeyFrame> orderedActions = new TreeMap<Integer, KeyFrame>();
      for (IAction action : shape.getActions()) {
        if (!(action instanceof KeyFrame)) {
          throw new IllegalStateException();
        }
        KeyFrame kf = (KeyFrame) action;
        orderedActions.put(action.getStartTick(), kf);
      }
      if (orderedActions.size() != 0) {
        for (int i = orderedActions.firstKey(); i <= orderedActions.lastKey(); i++) {
          ShapeDrawParam newShape = this.findShapeParams(shape, i, orderedActions);
          if (orderedShapes.containsKey(i)) {
            List<ShapeDrawParam> cShapes = orderedShapes.get(i);
            cShapes.add(newShape);
            orderedShapes.put(i, cShapes);
          } else {
            List<ShapeDrawParam> nShapes = new ArrayList<>();
            nShapes.add(newShape);
            orderedShapes.put(i, nShapes);
          }
        }
      }
    }
  }
   */

  @Override
  protected int[] findTicks(IShape s) {
    if (!(s instanceof ShapeWithKeyFrames)) {
      throw new IllegalStateException();
    }
    List<IAction> actions = s.getActions();
    int start = 0;
    int end = 0;
    for (IAction action : actions) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalStateException();
      }
      if (action.getStartTick() < start) {
        start = action.getStartTick();
      } else if (action.getStartTick() > end) {
        end = action.getStartTick();
      }
    }
    int[] outArr = new int[end - start];
    int j = 0;
    for (int i = start; i < end; i++) {
      outArr[j] = i;
      j++;
    }
    return outArr;
  }

  @Override
  protected ShapeDrawParam findShapeParams(IShape s, Integer tick) {
    if (!(s instanceof ShapeWithKeyFrames)) {
      throw new IllegalStateException();
    }
    IShapeWithKeyFrames shape = (ShapeWithKeyFrames) s;
    TreeMap<Integer, KeyFrame> orderedActions = shape.orderActions();
    if (orderedActions.containsKey(tick)) {
      KeyFrame kf = orderedActions.get(tick);
      return new ShapeDrawParam(shape.getType(), kf.getCoord("x", ""),
              kf.getCoord("y", ""), kf.getWidth(""), kf.getHeight(""),
              kf.getColor(""));
    } else {
      KeyFrame before = orderedActions.get(orderedActions.floorEntry(tick));
      KeyFrame after = orderedActions.get(orderedActions.ceilingEntry(tick));
      int newX = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getCoord("x", "start"),
              after.getCoord("x", "end"));
      int newY = linearInterpolation(before.getStartTick(), after.getEndTick(), tick,
              before.getCoord("y", "start"),
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
      return new ShapeDrawParam(shape.getType(), newX, newY, newWidth, newHeight,
              new RGBColor(newR, newG, newB));
    }
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
      this.shapes.get(name).addKeyFrame(new KeyFrame(tick, xCoord, yCoord,
              width, height, redGradient, greenGradient, blueGradient));
      this.orderByTick();
    } else {
      throw new IllegalArgumentException("Shape doesn't exist in the model");
    }
  }

  @Override
  public void removeKeyFrame(String name, int tick) {
    if (this.shapes.containsKey(name)) {
      this.shapes.get(name).removeKeyFrame(tick);
    } else {
      throw new IllegalArgumentException("Invalid inputs.");
    }
    this.orderByTick();
  }

  @Override
  public void removeShape(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with this name does not exist in the animation.");
    } else {
      this.shapes.remove(name);
    }
    this.orderByTick();
  }

  @Override
  public void editKeyFrame(String name, int tick, int xCoord,
                           int yCoord, int width, int height,
                           int redGradient, int greenGradient, int blueGradient) {
    if (this.shapes.containsKey(name)) {
      if (this.shapes.get(name).hasFrameAt(tick)) {
        this.shapes.get(name).editKeyFrame(tick, xCoord, yCoord, width, height,
                redGradient, greenGradient, blueGradient);
      }
      else {
        throw new IllegalArgumentException("Shape has no frame at this tick");
      }
    }
    else {
      throw new IllegalArgumentException("Shape doesn't exist in the animation.");
    }
    this.orderByTick();
  }

  @Override
  public ShapeWithKeyFrames getShape(String name) {
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
      this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
      this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
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
