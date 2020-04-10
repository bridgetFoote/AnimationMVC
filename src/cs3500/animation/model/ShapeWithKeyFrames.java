package cs3500.animation.model;

import java.util.*;

public class ShapeWithKeyFrames extends Shape implements IShapeWithKeyFrames {

  private TreeMap<Integer, KeyFrame> keyFrames;

  public ShapeWithKeyFrames(String name, ShapeType type) {
    super(name, type);
    this.keyFrames = new TreeMap<Integer, KeyFrame>();
  }



  @Override
  public void addKeyFrame(KeyFrame frame) {
    if (keyFrames.containsKey(frame.getStartTick())) {
      throw new IllegalArgumentException("Frame cannot be added.");
    }
    this.actions.add(frame);
    this.keyFrames.put(frame.getStartTick(), frame);
  }

  @Override
  public void removeKeyFrame(int tick) {
    if (keyFrames.containsKey(tick)) {
      this.actions.remove(keyFrames.get(tick));
      keyFrames.remove(tick);
    }
  }

  @Override
  public void editKeyFrame(int tick, int xCoord, int yCoord, int width,
                           int height, int redGradient, int greenGradient, int blueGradient) {
    if (keyFrames.containsKey(tick)) {
      KeyFrame oldFrame = new KeyFrame(this.keyFrames.get(tick));
      KeyFrame newFrame = new KeyFrame(this.keyFrames.get(tick));
      newFrame.edit(xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
      this.keyFrames.get(tick).edit(xCoord, yCoord, width, height, redGradient, greenGradient, blueGradient);
      this.actions.remove(oldFrame);
      this.actions.add(newFrame);
    }
  }

  @Override
  public boolean hasFrameAt(int tick) {
    return this.keyFrames.containsKey(tick);
  }

  @Override
  public KeyFrame getTickAt(int tick) {
    if (this.keyFrames.containsKey(tick)) {
      return this.keyFrames.get(tick);
    } else {
      throw new IllegalArgumentException("No frame exists at this tick");
    }
  }

  @Override
  public int getFirstTick() {
    if (this.keyFrames.size() == 0) {
      return 0;
    }
    return this.keyFrames.firstKey();
  }

  @Override
  public int getLastTick() {
    if (this.keyFrames.size() == 0) {
      return 0;
    }
    return this.keyFrames.lastKey();
  }

  @Override
  public KeyFrame getFrame(int tick) {
    if (this.hasFrameAt(tick)) {
      return new KeyFrame(this.keyFrames.get(tick));
    }
    throw new IllegalArgumentException("No frame exists at the given tick.");
  }

  @Override
  public KeyFrame getFrameBefore(int tick) {
    if ((this.getFirstTick() > tick) || (this.keyFrames.size() == 0)) {
      throw new IllegalArgumentException("No such frame exists.");
    }
    return this.keyFrames.floorEntry(tick).getValue();
  }

  @Override
  public KeyFrame getFrameAfter(int tick) {
    if (this.keyFrames.size() == 0) {
      throw new IllegalArgumentException("No such frame exists.");
    } else if (this.getLastTick() < tick) {
      return this.keyFrames.lastEntry().getValue();
    }
    return this.keyFrames.ceilingEntry(tick).getValue();
  }

  @Override
  public List<KeyFrame> getKeyframes() {
    List<KeyFrame> list = new ArrayList<KeyFrame>();
    for (KeyFrame kf : this.keyFrames.values()) {
      list.add(kf);
    }
    return list;
  }

  @Override
  public List<Integer> getPosition(int tick) {
    if (this.keyFrames.containsKey(tick)) {
      return Arrays.asList(this.keyFrames.get(tick).getCoord("x", ""),
              this.keyFrames.get(tick).getCoord("y", ""));
    } else if (tick < this.keyFrames.firstKey()) {
      throw new IllegalArgumentException("This shape does not have an action at the given tick.");
    } else if (tick > this.keyFrames.lastKey()) {
      return Arrays.asList(this.keyFrames.lastEntry().getValue().getCoord("x", ""),
              this.keyFrames.lastEntry().getValue().getCoord("y", ""));
    } else {
      KeyFrame before = this.keyFrames.floorEntry(tick).getValue();
      KeyFrame after = this.keyFrames.ceilingEntry(tick).getValue();
      int xCoord = this.linearInterpolation(before.getStartTick(), after.getStartTick(),
              tick, before.getCoord("x", ""), after.getCoord("x", ""));
      int yCoord = this.linearInterpolation(before.getStartTick(), after.getStartTick(),
              tick, before.getCoord("y", ""), after.getCoord("y", ""));
      return Arrays.asList(xCoord, yCoord);
    }
  }

  /**
   * Perform linear interpolation to find the value at a given tick according to.
   * f(t) = a(tb - t / tb - ta) + b (t - ta / tb - ta)
   * @param startTick ta
   * @param endTick tb
   * @param t t
   * @param startVal a
   * @param endVal b
   * @return f(t)
   */
  private int linearInterpolation(int startTick, int endTick, Integer t,
                                  int startVal, int endVal) {
    if (startTick == endTick) {
      return startVal;
    }
    return startVal * (endTick - t) / (endTick - startTick) + endVal *
            (t - startTick) / (endTick - startTick);
  }

}
