package cs3500.animation;

/**
 * Interface containing the methods used to modify an AnimationOperations,
 * any operational model must implement both AnimationOperations and
 * AnimationEditOperations.
 */
public interface AnimationEditOperations {

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
}
