package cs3500.animation.provider.view;

import cs3500.animation.model.KeyFrame;

public class Keyframe extends KeyFrame implements IMotion {

  public Keyframe(int tick, int xCoord, int yCoord, int width, int height, int redGradient, int greenGradient, int blueGradient) {
    super(tick, xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
  }

  protected int getTime() {
    return this.getStartTick();
  }
}
