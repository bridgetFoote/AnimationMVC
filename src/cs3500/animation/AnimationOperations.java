package cs3500.animation;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to represent an animation of shapes, does not contain any methods
 * necessary to modify the model.
 */
public interface AnimationOperations {

  /**
   * Sets the minimum x and y coordinate values,
   * as well as width and height for this animation.
   * @param x minimum x value.
   * @param y minimum y value.
   * @param width animation width.
   * @param height animation height.
   */
  public void setBounds(int x, int y, int width, int height);

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

  /**
   * Adds a new shape to the animation.
   * @param name is the shape's name
   * @throws IllegalArgumentException if a shape already in this animation
   *                                  has the given name or if the
   *                                  parameters are invalid.
   */
  void addShape(String name, String shapeType);

  /**
   * Removes the given shape from this animation if it exists.
   *
   * @param name is the name of the shape to remove.
   */
  void removeShape(String name);

  /**
   * Removes the given shape action from this animation if it exists.
   *
   * @param shapeName is the name of the shape this action belongs to.
   * @param startTick is the start tick for this action.
   * @param endTick is the end tick for this action.
   * @param startPointX is the x coordinate of the start point.
   * @param startPointY is the y coordinate of the start point.
   * @param endPointX is the x coordinate of the end point.
   * @param endPointY is the y coordinate of the end point.
   * @param startRedGradient is the red value of the starting color.
   * @param startGreenGradient is the green value of the starting color.
   * @param startBlueGradient is the blue value of the starting color.
   * @param endRedGradient is the red value of the ending color.
   * @param endGreenGradient is the green value of the ending color.
   * @param endBlueGradient is the blue value of the ending color.
   * @param startWidth is the starting width of the shape.
   * @param startHeight is the starting height of the shape.
   * @param endWidth is the end width of the shape.
   * @param endHeight is the end height of the shape.
   */
  void removeShapeAction(String shapeName, int startTick, int endTick, int startPointX,
                         int startPointY, int endPointX, int endPointY, int startRedGradient,
                         int startGreenGradient, int startBlueGradient, int endRedGradient,
                         int endGreenGradient, int endBlueGradient, int startWidth, int startHeight,
                         int endWidth, int endHeight);

  /**
   * Adds a new shape action to the animation.
   * @param shapeName is the name of the shape to add the action to.
   * @param startTick is the start tick for the action to add.
   * @param endTick is the end tick for the action to add.
   * @param startPointX is the x-coord of the starting point for the
   *                    action to add.
   * @param startPointY is the y-coord of the starting point for the
   *                    action to add.
   * @param endPointX is the x-coord of the ending point for the action
   *                  to add.
   * @param endPointY is the y-coord of the ending point for the action
   *                  to add.
   * @param startRedGradient is the starting red gradient value for the
   *                         action to add.
   * @param startGreenGradient is the starting green gradient value for
   *                           the action to add.
   * @param startBlueGradient is the starting blue gradient value for
   *                          the action to add.
   * @param endRedGradient is the ending red gradient value for the action
   *                       to add.
   * @param endGreenGradient is the ending green gradient value for the
   *                         action to add.
   * @param endBlueGradient is the ending blue gradient value for the
   *                        action to add.
   * @param startWidth is the starting width for the action to add.
   * @param startHeight is the starting height for the action to add.
   * @param endWidth is the ending width for the action to add.
   * @param endHeight is the ending height for the action to add.
   * @throws IllegalArgumentException if any of the arguments are invalid
   *                                  or if the given shape is not in this animation.
   */
  void addShapeAction(String shapeName, int startTick, int endTick, int startPointX,
                      int startPointY, int endPointX, int endPointY, int startRedGradient,
                      int startGreenGradient, int startBlueGradient, int endRedGradient,
                      int endGreenGradient, int endBlueGradient, int startWidth, int startHeight,
                      int endWidth, int endHeight);

  /**
   * Returns the width of the canvas for this animation.
   *
   * @return integer value of the width of this canvas.
   */
  int getCanvasWidth();

  /**
   * Returns the height of the canvas for this animation.
   *
   * @return integer value of the height of this canvas.
   */
  int getCanvasHeight();
}
