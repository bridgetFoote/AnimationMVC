package cs3500.animation;

import java.util.List;

/**
 * Represents the basic shape, has information about color,
 * width, height, and name.
 */
public interface IShape {

  /**
   * Returns the list of actions in this shape.
   *
   * @return list of shape actions, null if this shape has no actions.
   */
  List<ShapeAction> getActions();

  /**
   * Copy constructor for an IShape
   *
   * @return a copy of this shape.
   */
  IShape returnCopy();


  @Override
  String toString();

  /**
   * Returns the name of this shape.
   *
   * @return string of the name of the shape.
   */
  String getName();

  /**
   * Determines whether the given action can be added to this
   * shape.
   *
   * @param action the action to check.
   * @return true if the action can be added, false if this shape
   *         already has actions during the given action's time
   *         interval.
   */
  boolean validateAction(ShapeAction action);

  @Override
  boolean equals(Object other);

  @Override
  int hashCode();

  /**
   * Returns this shape's position at the given tick.
   *
   * @param tick is the tick to get this shape's position at.
   * @return the list of integers representing this shape's
   *         position.
   * @throws IllegalArgumentException if this shape doesn't
   *                                  have an action at the given tick.
   */
  List<Integer> getPosition(int tick);

  /**
   * Adds the given action to this shape.
   *
   * @param action the action to add.
   * @throws IllegalArgumentException if the given action is null
   *                                  or if this action overlaps with
   *                                  an action this shape already has.
   */
  void addShapeAction(ShapeAction action);


}
