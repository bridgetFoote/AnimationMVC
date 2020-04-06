package cs3500.animation.model;

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
  public static final class Builder implements AnimationBuilder<AnimationOperations> {
    private AnimationFrameOperations model = new AnimationFrameModel();

    @Override
    public AnimationOperations build() {
      this.model.orderByTick();
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationOperations> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addMotion(String name, int t1, int x1, int y1,
                                                           int w1, int h1, int r1,
                                                           int g1, int b1, int t2,
                                                           int x2, int y2, int w2, int h2,
                                                           int r2, int g2, int b2) {
      this.model.addShapeAction(name, t1, t2, x1, y1, x2, y2 ,r1, g1, b1, r2,
              g2, b2, w1, h1, w2,h2);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addKeyframe(String name, int t, int x, int y,
                                                             int w, int h, int r, int g, int b) {
      this.model.addKeyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}
