package cs3500.animation.model;

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
   * @param frame is the frame to remove.
   * @throws IllegalArgumentException is this frame does not exist for this shape.
   */
  void removeKeyFrame(KeyFrame frame);

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
}
