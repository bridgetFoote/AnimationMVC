package cs3500.animation.model;

import cs3500.animation.provider.view.IColor;
import cs3500.animation.provider.view.IShape;
import cs3500.animation.provider.view.Keyframe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter class between ShapeWithKeyFrame class and provider.view.IShape interface.
 */
public class SWKFToIShapeAdapter extends ShapeWithKeyFrames implements IShape {

  private Posn position;
  private int width;
  private int height;
  private IColor color;

  /**
   * Public contsructor, sets all params.
   *
   * @param name is the name of the shape.
   * @param type is the type of the shape.
   * @param p is the position of the shape.
   * @param w is the width of the shape.
   * @param h is the height of the shape.
   * @param c is the color of the shape.
   */
  public SWKFToIShapeAdapter(String name, ShapeType type, Posn p, double w, double h, IColor c) {
    super(name, type);
    if ((w <= 0) || (h <= 0)) {
      throw new IllegalArgumentException("The shape dimensions must be greater than 0");
    }
    this.position = p;
    this.width = (int) w;
    this.height = (int) h;
    this.color = c;
  }

  public SWKFToIShapeAdapter(IShapeWithKeyFrames s) {
    super(s.getName(), s.getType());
    for (IAction a : s.getActions()) {
      Keyframe beginning;
      Keyframe end;
      if (a.getStartTick() == a.getEndTick()) {
        this.actions.add(new Keyframe(a.getStartTick(), a.getCoord("x", "end"),
                a.getCoord("y", "end"), a.getWidth("end"), a.getHeight("end"),
                a.getColor("end").getColorGradient("red"),
                a.getColor("end").getColorGradient("green"),
                a.getColor("end").getColorGradient("blue")));
      } else {
        this.actions.add(new Keyframe(a.getStartTick(), a.getCoord("x", "start"),
                a.getCoord("y", "start"), a.getWidth("start"), a.getHeight("start"),
                a.getColor("start").getColorGradient("red"),
                a.getColor("start").getColorGradient("green"),
                a.getColor("start").getColorGradient("blue")));
        this.actions.add(new Keyframe(a.getStartTick(), a.getCoord("x", "end"),
                a.getCoord("y", "end"), a.getWidth("end"), a.getHeight("end"),
                a.getColor("end").getColorGradient("red"),
                a.getColor("end").getColorGradient("green"),
                a.getColor("end").getColorGradient("blue")));
      }

    }
  }

  @Override
  public IColor getColor() {
    return this.color;
  }

  @Override
  public List<Keyframe> getKeyframes() {
    List<Keyframe> frames = new ArrayList<Keyframe>();
    for (KeyFrame kf : this.actions) {
      frames.add(new Keyframe(kf));
    }
    return frames;
  }

  @Override
  public Posn getPosn() {
    return this.position;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void sortActions() {
    Collections.sort(this.actions, new CompareActions());
  }
}
