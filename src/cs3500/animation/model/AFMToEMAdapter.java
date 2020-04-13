package cs3500.animation.model;

import cs3500.animation.provider.view.ExCELlenceOperations;
import cs3500.animation.provider.view.IColor;
import cs3500.animation.provider.view.IMotion;
import cs3500.animation.provider.view.IShape;
import cs3500.animation.provider.view.Keyframe;

import java.util.*;

/**
 * Adapter class for AnimationFrame model class to ExCELlenceOperations interface.
 */
public class AFMToEMAdapter extends AnimationFrameModel implements ExCELlenceOperations {

  public AFMToEMAdapter() {
    super();
  }

  @Override
  public void create(String name, String shape, Posn p, double w, double h, IColor c) {
    ShapeType type;
    if (shape.equals("rectangle")) {
      type = ShapeType.RECTANGLE;
    } else if (shape.equals("ellipse")) {
      type = ShapeType.ELLIPSE;
    } else {
      throw new IllegalArgumentException("The given shape type is not valid.");
    }
    SWKFToIShapeAdapter s = new SWKFToIShapeAdapter(name, type, p, w, h, c);
    if (!this.shapes.containsKey(name)) {
      this.shapes.put(name, s);
    }
  }

  @Override
  public void addMotion(String name, IMotion m) {
    ShapeActionToIMotionAdapter ma = (ShapeActionToIMotionAdapter) m;
    this.addShapeAction(name, ma.getStartTick(), ma.getEndTick(),
            ma.getCoord("x", "start"),
            ma.getCoord("y", "start"),
            ma.getCoord("x", "end"),
            ma.getCoord("y", "end"),
            ma.getColor("start").getColorGradient("red"),
            ma.getColor("start").getColorGradient("green"),
            ma.getColor("start").getColorGradient("blue"),
            ma.getColor("end").getColorGradient("red"),
            ma.getColor("end").getColorGradient("green"),
            ma.getColor("end").getColorGradient("blue"),
            ma.getWidth("start"), ma.getHeight("start"),
            ma.getWidth("end"), ma.getHeight("end"));
  }

  @Override
  public void removeMotion(String name, IMotion m) {
    // not needed
  }

  @Override
  public ArrayList<IMotion> getMotions(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with the given name does not exist.");
    }
    ArrayList<IMotion> motions = new ArrayList<IMotion>();
    for (IAction a : this.shapes.get(name).getActions()) {
      motions.add(new ShapeActionToIMotionAdapter(a));
    }
    return motions;
  }

  @Override
  public void addKeyframe(String name, Keyframe k) {
    this.addKeyFrame(name, k.getStartTick(), k.getCoord("x", ""), k.getCoord("y", ""),
            k.getWidth(""), k.getHeight(""), k.getColor("").getColorGradient("red"),
            k.getColor("").getColorGradient("green"),
            k.getColor("").getColorGradient("blue"));
  }

  @Override
  public void removeKeyframe(String name, Keyframe k) {
    this.removeKeyFrame(name, k.getStartTick());
  }

  @Override
  public ArrayList<Keyframe> getKeyframes(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("This shape does not exist.");
    }
    ArrayList<Keyframe> frames = new ArrayList<Keyframe>();
    for (IAction action : this.shapes.get(name).getActions()) {
      if (!(action instanceof KeyFrame)) {
        throw new IllegalStateException();
      }
      Keyframe kf = new Keyframe(action.getStartTick(),
              action.getCoord("x", ""),
              action.getCoord("y", ""),
              action.getWidth(""), action.getHeight(""),
              action.getColor("").getColorGradient("red"),
              action.getColor("").getColorGradient("green"),
              action.getColor("").getColorGradient("blue"));
      frames.add(kf);
    }
    return frames;
  }

  @Override
  public ArrayList<cs3500.animation.provider.view.IShape> getShapesAt(int tick) {
    List<ShapeDrawParam> params = this.getShapesAtTick(tick);
    ArrayList<IShape> shapes = new ArrayList<IShape>();
    if (params.size() != 0) {
      for (ShapeDrawParam p : params) {
        shapes.add((SWKFToIShapeAdapter) this.shapes.get(p.name));
      }
    }
    return shapes;
  }

  @Override
  public ArrayList<cs3500.animation.provider.view.IShape> getShapesAtKeyframe(int tick) {
    List<ShapeDrawParam> params = this.getShapesAtTick(tick);
    ArrayList<IShape> shapes = new ArrayList<IShape>();
    if (params.size() != 0) {
      for (ShapeDrawParam p : params) {
        shapes.add((SWKFToIShapeAdapter) this.shapes.get(p.name));
      }
    }
    return shapes;
  }

  @Override
  public IShape getShape(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("Shape does not exist.");
    }
    return (SWKFToIShapeAdapter) this.shapes.get(name);
  }

  @Override
  public HashMap<String, cs3500.animation.provider.view.IShape> getShapes() {
    HashMap<String, IShape> map = new HashMap<String, IShape>();
    for (Map.Entry<String, IShapeWithKeyFrames> e : this.shapes.entrySet()) {
      map.put(e.getKey(), new SWKFToIShapeAdapter(e.getValue()));
    }
    return map;
  }

  @Override
  public String getState() {
    String out = this.toString();
    Map<cs3500.animation.model.IShape, List<IAction>> actions = this.getShapeActions();
    Set aKeys = actions.keySet();
    for (Object s: aKeys) {
      if (s instanceof cs3500.animation.model.IShape) {
        out = out.concat(s.toString());
      }
    }
    return out;
  }

  @Override
  public Posn getTopLeft() {
    return new Posn(this.topX, this.topY);
  }

  @Override
  public int getWindowWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getWindowHeight() {
    return this.canvasHeight;
  }

  @Override
  public String getKey(cs3500.animation.provider.view.IShape s) {
    if (!(s instanceof SWKFToIShapeAdapter)) {
      throw new IllegalStateException();
    }
    SWKFToIShapeAdapter shape = (SWKFToIShapeAdapter) s;
    return shape.getName();
  }

  @Override
  public void sortMotions(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalStateException();
    }
    SWKFToIShapeAdapter s = (SWKFToIShapeAdapter) this.shapes.get(name);
    s.sortActions();
  }

  @Override
  public void sortKeyframes(String name) {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalStateException();
    }
    SWKFToIShapeAdapter s = (SWKFToIShapeAdapter) this.shapes.get(name);
    s.sortActions();
  }

  @Override
  public void validateMotions(String name) {
    // not needed
  }

  @Override
  public void setTopLeft(Posn p) {
    this.topX = p.getX();
    this.topY = p.getY();
  }

  @Override
  public void setWindowWidth(int w) {
    this.canvasWidth = w;
  }

  @Override
  public void setWindowHeight(int h) {
    this.canvasHeight = h;
  }

  /**
   * Builds an Animation.
   */
  public static final class Builder implements AnimationBuilder<AFMToEMAdapter> {
    private AFMToEMAdapter model = new AFMToEMAdapter();

    @Override
    public AFMToEMAdapter build() {
      this.model.orderByTick();
      return this.model;
    }

    @Override
    public AnimationBuilder<AFMToEMAdapter> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AFMToEMAdapter> declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AFMToEMAdapter> addMotion(String name, int t1, int x1, int y1,
                                                                int w1, int h1, int r1,
                                                                int g1, int b1, int t2,
                                                                int x2, int y2, int w2, int h2,
                                                                int r2, int g2, int b2) {
      this.model.addShapeAction(name, t1, t2, x1, y1, x2, y2 ,r1, g1, b1, r2,
              g2, b2, w1, h1, w2,h2);
      return this;
    }

    @Override
    public AnimationBuilder<AFMToEMAdapter> addKeyframe(String name, int t, int x, int y,
                                                                  int w, int h, int r, int g, int b) {
      this.model.addKeyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}
