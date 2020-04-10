package cs3500.animation.provider.view;

import cs3500.animation.model.AnimationFrameModel;
import cs3500.animation.model.IAction;
import cs3500.animation.model.KeyFrame;
import cs3500.animation.model.ShapeType;

import java.util.ArrayList;
import java.util.HashMap;

public class ExCELlenceModel extends AnimationFrameModel implements ExCELlenceOperations {

  /**
   * Creates a new model with an empty map of shapes.
   */
  public ExCELlenceModel() {
    super();
  }

  @Override
  public void create(String name, String shape, Posn p, double w, double h, IColor c) {
    if (!(shapes.containsKey(name))) {
      this.addShape(name, shape);
      ShapeType t;
      if (shape.equals("rectangle")) {
        t = ShapeType.RECTANGLE;
      } else if (shape.equals("ellipse")) {
        t = ShapeType.ELLIPSE;
      } else {
        throw new IllegalArgumentException("Shape type is not valid.");
      }
      this.shapes.put(name, new Shape(name, t, c, (int) w, (int) h, p));
    }
  }

  @Override
  public void addMotion(String name, IMotion m) {
    this.addShapeAction(name, m.getStartTick(), m.getEndTick(),
            m.getCoord("x", "start"), m.getCoord("y", "start"),
            m.getCoord("x", "end"), m.getCoord("y", "end"),
            m.getColor("start").getColorGradient("red"),
            m.getColor("start").getColorGradient("green"),
            m.getColor("start").getColorGradient("blue"),
            m.getColor("end").getColorGradient("red"),
            m.getColor("end").getColorGradient("green"),
            m.getColor("end").getColorGradient("blue"),
            m.getWidth("start"), m.getHeight("start"),
            m.getWidth("end"), m.getHeight("end"));
  }

  @Override
  public void removeMotion(String name, IMotion m) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ArrayList<IMotion> getMotions(String name) {
    ArrayList<IMotion> list = new ArrayList<IMotion>();
    for (IAction action : this.getShapeActions().get(name)) {
      if (!(action instanceof IMotion)) {
        throw new IllegalStateException();
      }
      IMotion motion = (IMotion) action;
      list.add(motion);
    }
    return list;
  }

  @Override
  public void addKeyframe(String name, KeyFrame k) {
    this.addKeyFrame(name, k.getStartTick(), k.getCoord("x", ""),
            k.getCoord("y", ""), k.getWidth(""),
            k.getHeight(""), k.getColor("").getColorGradient("red"),
            k.getColor("").getColorGradient("green"),
            k.getColor("").getColorGradient("blue"));
  }

  @Override
  public void removeKeyframe(String name, KeyFrame k) {
    this.removeKeyFrame(name, k.getStartTick());
  }

  @Override
  public ArrayList<KeyFrame> getKeyframes(String name) {
    if (this.shapes.containsKey(name)) {
      if (!(this.shapes.get(name) instanceof IShape)) {
        throw new IllegalStateException();
      }
      IShape shape = (IShape) this.shapes.get(name);
      return new ArrayList<KeyFrame>(shape.getKeyframes());
    }
    throw new IllegalArgumentException("No shape exists with this name.");
  }

  @Override
  public ArrayList<IShape> getShapesAt(int tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ArrayList<IShape> getShapesAtKeyframe(int tick) {

  }

  @Override
  public HashMap<String, IShape> getShapes() {
    return null;
  }

  @Override
  public String getState() {
    return null;
  }

  @Override
  public Posn getTopLeft() {
    return null;
  }

  @Override
  public int getWindowWidth() {
    return 0;
  }

  @Override
  public int getWindowHeight() {
    return 0;
  }

  @Override
  public String getKey(IShape s) {
    return null;
  }

  @Override
  public void sortMotions(String name) {

  }

  @Override
  public void sortKeyframes(String name) {

  }

  @Override
  public void validateMotions(String name) {

  }

  @Override
  public void setTopLeft(Posn p) {

  }

  @Override
  public void setWindowWidth(int w) {

  }

  @Override
  public void setWindowHeight(int h) {

  }
}
