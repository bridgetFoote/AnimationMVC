package cs3500.animation.model;

import cs3500.animation.provider.view.ExCELlenceOperations;

import java.security.Key;
import java.util.List;

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
   * @throws IllegalArgumentException if there is no shape of the given name in the
   *                                  animation or if the keyframe described does not exist
   *                                  for the given shape.
   */
  void removeKeyFrame(String name, int tick);

  /**
   * Removes the shape with the given name from the animation.
   *
   * @param name is the name of the shape to remove.
   * @throws IllegalArgumentException if there is no shape with the given name in the animation.
   */
  void removeShape(String name);

  /**
   * Edits the key frame at the given tick in the shape with the given name.
   *
   * @param name is the name of the shape.
   * @param tick is the tick of the key frame to edit.
   * @param xCoord is the new x coordinate or null if it will not change.
   * @param yCoord is the new y coordinate or null if it will not change.
   * @param width is the new width or null if it will not change.
   * @param height is the new height or null if it will not change.
   * @param redGradient is the new red gradient or null if it will not change.
   * @param greenGradient is the new green gradient or null if it will not change.
   * @param blueGradient is the new blue gradient or null if it will not change.
   * @throws IllegalArgumentException if a shape with the given name doesn't exist in this animation
   *                                  or said shape does not have any key frame at the given tick or
   *                                  all of the other inputs are null.
   */
  void editKeyFrame(String name, int tick, int xCoord, int yCoord, int width, int height,
                    int redGradient, int greenGradient, int blueGradient);

  /**
   * Get the shape from the model given the name.
   * @param name the name of the shape
   * @return the shape
   * @throws IllegalArgumentException if the shape is not in the model.
   */
  ShapeWithKeyFrames getShapeWithName(String name);

}
