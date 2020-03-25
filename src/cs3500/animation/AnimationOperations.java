package cs3500.animation;

import java.util.HashMap;
import java.util.List;

/**
 * Interface to represent an animation of shapes, does not contain any methods
 * necessary to modify the model.
 */
public interface AnimationOperations {

  /**
   * Set the canvas for the views.
   * @param x the top x coordinate
   * @param y the top y coordinate
   * @param width the width of the canvas
   * @param height the height of the canvas
   * @throws IllegalArgumentException if any of the params are negative
   */
  public void setCanvas(int x, int y, int width, int height);

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
   * @param endWidth is the ending width for the action to add.
   * @param endHeight is the ending height for the action to add.
   * @throws IllegalArgumentException if any of the arguments are invalid
   *                                  or if the given shape is not in this animation.
   */
  void addShapeAction(String shapeName, int startTick, int endTick, int startPointX,
                      int startPointY, int endPointX, int endPointY, int startRedGradient,
                      int startGreenGradient, int startBlueGradient, int endRedGradient,
                      int endGreenGradient, int endBlueGradient, int startWidth,
                      int startHeight, int endWidth, int endHeight);

  /**
   * Use a tree map to order the shapes in the model based on tick.
   * This will allow for easier look-up upon animation.
   * N.B. This will be called right before the builder builds the model
   */
  void orderByTick();
}
