package cs3500.animation;

import java.util.List;

/**
 * Interface containing the methods used to modify an AnimationOperations,
 * any operational model must implement both AnimationOperations and
 * AnimationEditOperations.
 */
public interface AnimationEditOperations {

  /**
   * Adds a new shape to the animation.
   * @param redGradient is the amount of red in the shape's color.
   * @param greenGradient is the amount of red in the shape's color.
   * @param blueGradient is the amount of red in the shape's color.
   * @param width is the width of the shape.
   * @param height is the height of the shape.
   * @param name is the shape's name
   * @throws IllegalArgumentException if a shape already in this animation
   *                                  has the given name or if the
   *                                  parameters are invalid.
   */
  void addShape(int redGradient, int greenGradient, int blueGradient,
                int width, int height, String name, String shapeType);

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
                      int endGreenGradient, int endBlueGradient, double endWidth,
                      double endHeight);
}
