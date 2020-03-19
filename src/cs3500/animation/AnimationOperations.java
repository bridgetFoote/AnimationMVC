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
  List<SimpleShape> getShapes();

  /**
   * Returns a map of the shapes in this animation with their corresponding actions.
   *
   * @return a hashmap connecting all of the shapes in this animation with their actions,
   *         returns an empty map if there are no shapes, and the value for a shape with
   *         no actions is the empty list.
   */
  HashMap<SimpleShape, List<ShapeAction>> getShapeActions();
}
