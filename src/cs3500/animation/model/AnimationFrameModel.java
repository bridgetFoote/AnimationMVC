package cs3500.animation.model;

import java.util.HashMap;
import java.util.List;

/**
 * Represents an animation where the motions of the shapes are represented by
 * keyframes where the shape's characteristics at any given tick are found
 * by tweening.
 */
public class AnimationFrameModel extends AnimationModel implements AnimationFrameOperations {

  public AnimationFrameModel() {
    super();
  }

  @Override
  public void addKeyFrame(String name, int tick, int xCoord,
                          int yCoord, int width, int height, int redGradient,
                          int greenGradient, int blueGradient) {

  }

  @Override
  public void removeKeyFrame(String name, int tick, int xCoord,
                             int yCoord, int width, int height, int redGradient,
                             int greenGradient, int blueGradient) {

  }
}
