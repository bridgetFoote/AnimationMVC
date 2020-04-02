package cs3500.animation.model;

/**
 * Interface to represent an animation of shapes where the actions of
 * the shapes are represented by keyframes.
 */
public interface AnimationFrameOperations extends AnimationOperations {

  /**
   * Adds a keyframe with the given parameters to the shape with the given name.
   *
   * @param name is the name of the shape.
   * @param tick is the tick for the frame.
   * @param xCoord is the x coordinate for the shape in the frame.
   * @param yCoord is the y coordinate for the shape in the frame.
   * @param width is the width of the shape in the frame.
   * @param height is the height of the shape in the frame.
   * @param redGradient is the red value for the color of the shape in the frame.
   * @param greenGradient is the green value for the color of the shape in the frame.
   * @param blueGradient is the blue value for the color of the shape in the frame.
   * @throws IllegalArgumentException if there is no shape of the given name in the
   *                                  animation or if any of the keyframe inputs are invalid.
   */
  void addKeyFrame(String name, int tick, int xCoord, int yCoord, int width,
                   int height, int redGradient, int greenGradient, int blueGradient);

  /**
   * Removes the keyframe with the given parameters from the shape with the given name.
   *
   * @param name is the name of the shape.
   * @param tick is the tick for the frame.
   * @param xCoord is the x coordinate for the shape in the frame.
   * @param yCoord is the y coordinate for the shape in the frame.
   * @param width is the width of the shape in the frame.
   * @param height is the height of the shape in the frame.
   * @param redGradient is the red value for the color of the shape in the frame.
   * @param greenGradient is the green value for the color of the shape in the frame.
   * @param blueGradient is the blue value for the color of the shape in the frame.
   * @throws IllegalArgumentException if there is no shape of the given name in the
   *                                  animation or if the keyframe described does not exist
   *                                  for the given shape.
   */
  void removeKeyFrame(String name, int tick, int xCoord, int yCoord, int width,
                      int height, int redGradient, int greenGradient, int blueGradient);
}
