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
}
