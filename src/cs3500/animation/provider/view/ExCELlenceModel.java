package cs3500.animation.provider.view;

import cs3500.animation.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
      ShapeType type;
      if (shape.equals("rectangle")) {
        type = ShapeType.RECTANGLE;
      } else if (shape.equals("ellipse")) {
        type = ShapeType.ELLIPSE;
      } else {
        throw new IllegalArgumentException("Shape type is not valid");
      }
      IShape s = new Shape(name, type);
      this.shapes.put(name, s);
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
    List<ShapeDrawParam> params = this.getShapesAtTick(tick);
    ArrayList<IShape> sl = new ArrayList<IShape>();
    for (ShapeDrawParam p : params) {
      String name = p.name;
      IShape shape = (IShape) this.shapes.get(name);
      sl.add(shape);
    }
    return sl;
  }

  @Override
  public ArrayList<IShape> getShapesAtKeyframe(int tick) {
    List<ShapeDrawParam> params = this.getShapesAtTick(tick);
    ArrayList<IShape> sl = new ArrayList<IShape>();
    for (ShapeDrawParam p : params) {
      String name = p.name;
      IShape shape = (IShape) this.shapes.get(name);
      sl.add(shape);
    }
    return sl;
  }

  @Override
  public IShape getShape(String name) {
    return (IShape) this.shapes.get(name);
  }

  @Override
  public HashMap<String, IShape> getShapes() {
    HashMap<String, IShape> rl = new HashMap<String, IShape>();
    for (String name : this.shapes.keySet()) {
      rl.put(name, (cs3500.animation.provider.view.IShape) this.shapes.get(name));
    }
    return rl;
  }

  @Override
  public String getState() {
    throw new UnsupportedOperationException();
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
  public String getKey(IShape s) {
    return s.getName();
  }

  @Override
  public void sortMotions(String name) {
    this.orderByTick();
  }

  @Override
  public void sortKeyframes(String name) {
    this.orderByTick();
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
    if (w <= 0) {
      throw new IllegalArgumentException("Canvas width must be greater than 0.");
    }
    this.canvasWidth = w;
  }

  @Override
  public void setWindowHeight(int h) {
    if (h <= 0) {
      throw new IllegalArgumentException("Canvas height must be greater than 0");
    }
    this.canvasHeight = h;
  }

  /**
   * Builds an Animation.
   */
  public static final class Builder implements AnimationBuilder<ExCELlenceOperations> {
    private ExCELlenceOperations model = new ExCELlenceModel();

    @Override
    public ExCELlenceOperations build() {
      this.model.sortKeyframes("");
      return this.model;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> setBounds(int x, int y, int width, int height) {
      this.model.setTopLeft(new Posn(x, y));
      this.model.setWindowWidth(width);
      this.model.setWindowHeight(height);
      return this;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> declareShape(String name, String type) {
      this.model.create(name, type, null, 0, 0, null);
      return this;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> addMotion(String name, int t1, int x1, int y1,
                                                                int w1, int h1, int r1,
                                                                int g1, int b1, int t2,
                                                                int x2, int y2, int w2, int h2,
                                                                int r2, int g2, int b2) {
      if ((x1 == x2) && (y1 == y2)) {
        this.model.addMotion(name, new Stay(t1, t2, Arrays.asList(x1, y1),
                Arrays.asList(x2, y2), new RGBColor(r1, g1, b1), new RGBColor(r2, g2, b2), w1, h1, w2, h2));
      }
      this.model.addMotion(name, new Move(t1, t2, Arrays.asList(x1, y1),
              Arrays.asList(x2, y2), new RGBColor(r1, g1, b1), new RGBColor(r2, g2, b2), w1, h1, w2, h2));
      return this;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> addKeyframe(String name, int t, int x, int y,
                                                                  int w, int h, int r, int g, int b) {
      this.model.addKeyframe(name, new KeyFrame(t, x, y, w, h, r, g, b));
      return this;
    }
  }
}
