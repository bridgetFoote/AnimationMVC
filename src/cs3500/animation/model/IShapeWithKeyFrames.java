package cs3500.animation.model;

import java.util.List;

public interface IShapeWithKeyFrames extends IShape {

  /**
   * Adds the given keyframe to this shape.
   *
   * @param frame is the frame to add.
   * @throws IllegalArgumentException is this shape already has a frame
   *                                  at the same tick.
   */
  void addKeyFrame(KeyFrame frame);

  /**
   * Removes the given keyframe to this shape.
   *
   * @param tick is the tick of the frame to remove.
   * @throws IllegalArgumentException is this frame does not exist for this shape.
   */
  void removeKeyFrame(int tick);

  /**
   * Edits the key frame at the given tick.
   *
   * @param tick is the tick of the key frame to edit.
   * @param xCoord is the new x coordinate or null.
   * @param yCoord is the new y coordinate or null.
   * @param width is the new width or null.
   * @param height is the new height or null.
   * @param redGradient is the new red gradient or null.
   * @param greenGradient is the new green gradient or null.
   * @param blueGradient is the new blue gradient or null.
   * @throws IllegalArgumentException if a frame does not exist at the given tick
   *                                  or if all other inputs are null.
   */
  void editKeyFrame(int tick, int xCoord, int yCoord, int width,
                    int height, int redGradient, int greenGradient, int blueGradient);

  /**
   * Returns whether this shape has a key frame at the given tick.
   *
   * @param tick is the tick to check for a frame at.
   * @return true if there is a frame, false otherwise.
   */
  boolean hasFrameAt(int tick);

  /**
   * Return the frame for this shape at a given tick.
   *
   * @param tick the tick.
   * @return the keyframe for the shape at that tick.
   * @throws IllegalArgumentException if the shape doesn't have a frame at that tick.
   */
  KeyFrame getTickAt(int tick);

  /**
   * Returns the first tick that this shape has a motion.
   *
   * @return integer of the first tick or 0 if this shape has no actions.
   */
  int getFirstTick();

  /**
   * Returns the last tick that this shape has a motion.
   *
   * @return integer of the last tick or 0 of this shape has no actions.
   */
  int getLastTick();

  /**
   * Returns a copy of the frame the shape has at the given tick.
   *
   * @param tick the tick of the frame.
   * @return the keyframe at the given tick.
   * @throws IllegalArgumentException if the shape doesn't have a frame at the tick.
   */
  KeyFrame getFrame(int tick);

  /**
   * Returns a copy of the last frame whose tick is less than the given tick.
   *
   * @param tick is the tick to compare.
   * @return the KeyFrame that fits the above description.
   * @throws IllegalArgumentException if no such frame exists.
   */
  KeyFrame getFrameBefore(int tick);

  /**
   * Returns a copy of the first frame whose tick is greater than the given tick.
   *
   * @param tick is the tick to compare.
   * @return the KeyFrame that fits the above description.
   * @throws IllegalArgumentException if no such frame exists.
   */
  KeyFrame getFrameAfter(int tick);

  /**
   * Returns the list of keyframes for this shape.
   *
   * @return list of keyframes for this shape.
   */
  List<KeyFrame> getKeyframes();
}
