package cs3500.animation;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to represent an animation of shapes, does not contain any methods
 * necessary to modify the model.
 */
public interface AnimationOperations {

  /**
   * Returns a text description of the movement and properties
   * of each shape in the animation over the entire duration
   * of time.
   *
   * @return string representing the animation.
   */
  String getTextDescription();

  /**
   * Returns the list of shapes in this animation.
   *
   * @return a list of the shapes in this animation, list is empty if there are no shapes.
   */
  List<IShape> getShapes();

  /**
   * Returns a map of the shapes in this animation with their corresponding actions.
   *
   * @return a hashmap connecting all of the shapes in this animation with their actions,
   *         returns an empty map if there are no shapes, and the value for a shape with
   *         no actions is the empty list.
   */
  HashMap<IShape, List<ShapeAction>> getShapeActions();

  /**
   * Returns a list of all the shapes at the given tick.
   *
   * @param tick the tick to get the shapes at.
   * @return a list of all the shapes, empty if there are no shapes in this animation.
   * @throws IllegalArgumentException if the tick is invalid.
   */
  List<IShape> getShapesAtTick(int tick);
}
