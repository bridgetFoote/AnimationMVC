package cs3500.animation.model;

import java.util.HashMap;
import java.util.List;

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
    IShape shape = new ShapeWithKeyFrames(name, type);

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

  }

  @Override
  public void removeKeyFrame(String name, int tick, int xCoord,
                             int yCoord, int width, int height, int redGradient,
                             int greenGradient, int blueGradient) {

  }
}
