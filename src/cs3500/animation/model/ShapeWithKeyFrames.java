package cs3500.animation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShapeWithKeyFrames extends Shape implements IShapeWithKeyFrames {
  private List<KeyFrame> actions;

  public ShapeWithKeyFrames(String name, ShapeType type) {
    super(name, type);
    this.actions = new ArrayList<>();
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
  public void removeKeyFrame(int tick) {
    int removeIndex = -1;
    for (int i = 0; i < actions.size(); i++) {
      if (actions.get(i).getStartTick() == tick) {
        removeIndex = i;
      }
    }
    if (removeIndex == -1) {
      throw new IllegalArgumentException("No frame exists for this tick");
    }
    actions.remove(removeIndex);
  }

  @Override
  public void editKeyFrame(int tick, int xCoord, int yCoord, int width,
                           int height, int redGradient, int greenGradient, int blueGradient) {
    for (KeyFrame kf: this.actions) {
      if (kf.getStartTick() == tick) {
        kf.edit(xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
        return;
      }
    }
    throw new IllegalArgumentException("Frame does not exist at this tick");
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

  @Override
  public KeyFrame getTickAt(int tick) {
    for (KeyFrame kf: actions) {
      if (kf.getStartTick() == tick) {
        return kf;
      }
    }
    throw new IllegalArgumentException("No frame exists at this tick");
  }

}
