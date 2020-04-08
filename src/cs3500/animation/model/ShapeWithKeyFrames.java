package cs3500.animation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShapeWithKeyFrames extends Shape implements IShapeWithKeyFrames {

  public ShapeWithKeyFrames(String name, ShapeType type) {
    super(name, type);
    this.actions = new ArrayList<>();
  }



  @Override
  public void addKeyFrame(KeyFrame frame) {
    for (IAction action: this.actions) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalArgumentException("Shape actions have invalid type.");
      }
      KeyFrame kf = (KeyFrame) action;
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
    for (IAction action: this.actions) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalArgumentException("Shape actions have invalid type.");
      }
      KeyFrame kf = (KeyFrame) action;
      if (kf.getStartTick() == tick) {
        kf.edit(xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
        return;
      }
    }
    throw new IllegalArgumentException("Frame does not exist at this tick");
  }

  @Override
  public boolean hasFrameAt(int tick) {
    for (IAction action: this.actions) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalArgumentException("Shape actions have invalid type.");
      }
      KeyFrame kf = (KeyFrame) action;
      if (kf.getStartTick() == tick) {
        return true;
      }
    }
    return false;
  }

  @Override
  public KeyFrame getTickAt(int tick) {
    for (IAction action: actions) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalArgumentException("Shape actions have invalid type.");
      }
      KeyFrame kf = (KeyFrame) action;
      if (kf.getStartTick() == tick) {
        return kf;
      }
    }
    throw new IllegalArgumentException("No frame exists at this tick");
  }

  @Override
  public void convertActionsToKeyFrames() {
    List<IAction> newActions = new ArrayList<IAction>();
    for (IAction action: this.actions) {
      if (action instanceof ShapeAction) {
        KeyFrame start = new KeyFrame(action.getStartTick(),
                action.getCoord("x", "start"),
                action.getCoord("y", "start"),
                action.getWidth("start"),
                action.getHeight("start"),
                action.getColor("start").getColorGradient("red"),
                action.getColor("start").getColorGradient("green"),
                action.getColor("start").getColorGradient("blue"));
        KeyFrame end = new KeyFrame(action.getStartTick(),
                action.getCoord("x", "end"),
                action.getCoord("y", "end"),
                action.getWidth("end"),
                action.getHeight("end"),
                action.getColor("end").getColorGradient("red"),
                action.getColor("end").getColorGradient("green"),
                action.getColor("end").getColorGradient("blue"));
        newActions.add(start);
        newActions.add(end);
      } else {
        this.actions.add(action);
      }
      this.actions = newActions;
    }
  }

}
