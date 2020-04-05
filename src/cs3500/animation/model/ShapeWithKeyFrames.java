package cs3500.animation.model;

import java.util.List;
import java.util.Objects;

public class ShapeWithKeyFrames extends Shape implements IShapeWithKeyFrames {
  private List<KeyFrame> actions;

  public ShapeWithKeyFrames(String name, ShapeType type) {
    super(name, type);
  }



  @Override
  public void addKeyFrame(KeyFrame frame) {
    for (KeyFrame kf: this.actions) {
      if (frame.getStartTick() == kf.getStartTick()) {
        throw new IllegalArgumentException("Frame cannot be added.");
      }
    }
    this.actions.add(frame);
  }

  @Override
  public void removeKeyFrame(KeyFrame frame) {
    for (KeyFrame kf: this.actions) {
      if (kf.equals(frame)) {
        this.actions.remove(kf);
      }
    }
    throw new IllegalArgumentException("Frame does not exist.");
  }

  @Override
  public void editKeyFrame(int tick, int xCoord, int yCoord, int width,
                           int height, int redGradient, int greenGradient, int blueGradient) {
    for (KeyFrame kf: this.actions) {
      if (kf.getStartTick() == tick) {
        kf.edit(xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
      }
    }
  }

  @Override
  public boolean hasFrameAt(int tick) {
    for (KeyFrame kf: this.actions) {
      if (kf.getStartTick() == tick) {
        return true;
      }
    }
    return false;
  }
}
