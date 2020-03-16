package cs3500.animation;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to represent an animation of shapes.
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
   * Adds a new shape to the animation.
   * @param shape is the shape to be added.
   * @throws IllegalArgumentException if the given shape is null or if
   *                                  the given shape has the same name
   *                                  as a shape already in this animation.
   */
  void addShape(SimpleShape shape);

  /**
   * Adds a new shape action to the animation.
   * @param shape is the shape to add the action to.
   * @param action is the action to add.
   * @throws IllegalArgumentException if either of the arguments are null
   *                                  or if the given shape is not in this animation.
   */
  void addShapeAction(SimpleShape shape, ShapeAction action);

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
