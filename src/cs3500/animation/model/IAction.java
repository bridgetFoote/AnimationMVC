package cs3500.animation.model;

import java.util.List;

/**
 * Represents an transformation for a shape in the model. 
 */
public interface IAction {

  /**
   * Returns either the starting or ending color of the shape in this action.
   *
   * @param startOrEnd is either start or end, irrelevant if this is a key frame.
   * @return the indicated color value.
   */
  RGBColor getColor(String startOrEnd);

  /**
   * Returns a copy of this action.
   *
   * @return a complete copy of this action.
   */
  IAction returnCopy();

  /**
   * Returns the indicated width value.
   *
   * @param startOrEnd indicates whether to return the start or the end width,
   *                   irrelevant for KeyFrame.
   * @return value for the width.
   */
  int getWidth(String startOrEnd);

  /**
   * Returns the indicated height value.
   *
   * @param startOrEnd indicates whether to return the start or the end height,
   *                   irrelevant for KeyFrame.
   * @return value for the height.
   */
  int getHeight(String startOrEnd);

  /**
   * Returns the start tick for this shape action.
   *
   * @return the start tick if this is a ShapeAction,
   *         the tick value if it is a KeyFrame.
   */
  int getStartTick();

  /**
   * Returns the indicated coordinate value for this shape action.
   *
   * @param xOrY indicates whether to return the x or y value.
   * @param startOrEnd indicates whether to return the start or end coordinate,
   *                   irrelevant for a KeyFrame.
   * @return the indicated coordinate value.
   */
  int getCoord(String xOrY, String startOrEnd);

  /**
   * Returns the end tick for this shape action.
   *
   * @return the end tick if this is a ShapeAction,
   *         the tick value if it is a KeyFrame.
   */
  int getEndTick();

  /**
   * Returns the type of this action.
   *
   * @return the type of this action, either Move, Stay, or KeyFrame.
   */
  ActionType getActionType();

  /**
   * Determines whether this action overlaps with the given action in time.
   *
   * @param other is the action to compare to.
   * @return true if there is overlap, false otherwise.
   */
  boolean hasOverlap(IAction other);

  /**
   * Returns true if adding this action to a shape that has the given action
   * will cause the shape to teleport.
   *
   * @param other the action to compare with.
   * @return true if teleportation is inevitable, false otherwise.
   */
  boolean causesTeleportation(IAction other);

  /**
   * Converts this action to a string with respect to the given shape.
   *
   * @param shape is the shape that has this action.
   * @return the string representing this action, only relevant for ShapeAction.
   */
  String toString(IShape shape);

  /**
   * Returns the tick of this action, only implemented in keyframe not ShapeAction.
   *
   * @return the tick of this key frame.
   * @throws UnsupportedOperationException if this is a shapeAction
   */
  int getTime();
}
