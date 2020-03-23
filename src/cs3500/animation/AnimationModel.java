package cs3500.animation;

import java.util.*;

/**
 * Class to represent an animation of shapes.
 * Has information about shape size, color,
 * and movement over a period of time.
 */
public class AnimationModel implements AnimationOperations {

  /**
   * Creates a new AnimationModel with an empty list of shapes.
   */
  public AnimationModel() {
    shapes = new HashMap<String, IShape>();
    orderedShapes = new TreeMap<IShape, List<Integer>>();
  }

  private HashMap<String, IShape> shapes;
  private TreeMap<IShape, List<Integer>> orderedShapes;
  private int xMin;
  private int yMin;
  private int canvasWidth;
  private int canvasHeight;

  /**
   * Builds an AnimationOperations model from a text file
   */
  public static final class Builder implements AnimationBuilder<AnimationOperations> {

    public AnimationOperations model = new AnimationModel();

    @Override
    public AnimationOperations build() {
      return model;
    }

    @Override
    public AnimationBuilder<AnimationOperations> setBounds(int x, int y, int width, int height) {
      model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> declareShape(String name, String type) {
      model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addMotion(String name, int t1, int x1,
                                                           int y1, int w1, int h1, int r1, int g1,
                                                           int b1, int t2, int x2, int y2, int w2,
                                                           int h2, int r2, int g2, int b2) {
      model.addShapeAction(name, t1, t2, x1, y1, x2, y2, r1, g1, b1, r2, g2, b2, w1, h1, w2, h2);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addKeyframe(String name, int t, int x, int y,
                                                             int w, int h, int r, int g, int b) {
      return this;
    }
  }

  @Override
  public void addShape(String name, String shapeType) {
    IShape shape;
    if (ShapeType.RECTANGLE.str.equals(shapeType)) {
      shape = new Rectangle(name);
    } else if (ShapeType.ELLIPSE.str.equals(shapeType)) {
      shape = new Ellipse(name);
    } else {
      throw new IllegalArgumentException("Invalid shape type.");
    }

    if (this.checkForDuplicateShapeNames(shape.getName())) {
      throw new IllegalArgumentException("A shape with this name already "
              + "exists in this animation.");
    }
    this.shapes.put(shape.getName(), shape);
  }

  @Override
  public void removeShape(String name) {
    if (this.shapes.containsKey(name)) {
      this.orderedShapes.remove(this.shapes.get(name));
      this.shapes.remove(name);
    }
  }

  @Override
  public void removeShapeAction(String shapeName, int startTick, int endTick,
                                int startPointX, int startPointY, int endPointX,
                                int endPointY, int startRedGradient, int startGreenGradient,
                                int startBlueGradient, int endRedGradient, int endGreenGradient,
                                int endBlueGradient, int startWidth, int startHeight,
                                int endWidth, int endHeight) {
    if ((startTick < 0) || (endTick < 0) || (endTick < startTick) || (startPointX < 0)
            || (startPointY < 0) || (endPointX < 0)
            || (endPointY < 0) || (startRedGradient < 0) || (startRedGradient > 255)
            || (startGreenGradient < 0) || (startGreenGradient > 255)
            || (startBlueGradient < 0) || (startBlueGradient > 255) || (endRedGradient < 0)
            || (endRedGradient > 255) || (endGreenGradient < 0)
            || (endGreenGradient > 255) || (endBlueGradient < 0) || (endBlueGradient > 255)
            || (startWidth <= 0) || (startHeight <=  0)
            || (endWidth <= 0) || (endHeight <= 0)) {
      throw new IllegalArgumentException("Action inputs are not valid.");
    }
    if (this.shapes.containsKey(shapeName)) {
      ShapeAction a;
      if ((startPointX == endPointX) && (startPointY == endPointY)) {
        a = new Stay(startTick, endTick, Arrays.asList(startPointX, startPointY), Arrays.asList(endPointX, endPointY),
                new RGBColor(startRedGradient, startGreenGradient, startBlueGradient),
                new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
                startWidth, startHeight, endWidth, endHeight);
      } else {
        a = new Move(startTick, endTick, Arrays.asList(startPointX, startPointY), Arrays.asList(endPointX, endPointY),
                new RGBColor(startRedGradient, startGreenGradient, startBlueGradient),
                new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
                startWidth, startHeight, endWidth, endHeight);
      }
      if (this.shapes.get(shapeName).getActions().contains(a)) {
        this.shapes.get(shapeName).removeShapeAction(a);
      }
    }
  }

  @Override
  public void addShapeAction(String shapeName, int startTick, int endTick, int startPointX,
                             int startPointY, int endPointX, int endPointY, int startRedGradient,
                             int startGreenGradient, int startBlueGradient, int endRedGradient,
                             int endGreenGradient, int endBlueGradient, int startWidth, int startHeight,
                             int endWidth, int endHeight) {
    if (!this.shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("The given shape does not exist in this animation.");
    }
    ShapeAction action;
    if (this.determineActionType(startPointX, startPointY, endPointX, endPointY).equals(ActionType.MOVE)) {
      action = new Move(startTick, endTick, Arrays.asList(startPointX, startPointY),
              Arrays.asList(endPointX, endPointY), new RGBColor(startRedGradient, startGreenGradient,
              startBlueGradient), new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
              startWidth, startHeight, endWidth, endHeight);
    } else {
      action = new Stay(startTick, endTick, Arrays.asList(startPointX, startPointY),
              Arrays.asList(endPointX, endPointY), new RGBColor(startRedGradient, startGreenGradient,
              startBlueGradient), new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
              startWidth, startHeight, endWidth, endHeight);
    }


    if (this.shapes.get(shapeName).validateAction(action)) {
      if (this.shapes.get(shapeName).getActions().size() == 0) {
        this.shapes.get(shapeName).addShapeAction(action);
      }
    }
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    if ((x < 0) || (y < 0) || (width <= 0) || (height <= 0)) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    this.xMin = x;
    this.yMin = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public List<IShape> getShapes() {
    List<IShape> list = new ArrayList<IShape>();
    for (IShape s: this.shapes.values()) {
      list.add(s.returnCopy());
    }
    return list;
  }

  @Override
  public HashMap<IShape, List<ShapeAction>> getShapeActions() {
    HashMap<IShape, List<ShapeAction>> map = new HashMap<IShape, List<ShapeAction>>();

    for (IShape s: this.shapes.values()) {
      map.put(s, s.getActions());
    }

    return map;
  }

  @Override
  public List<IShape> getShapesAtTick(int tick) {
    List<IShape> shapesAtTick = new ArrayList<IShape>();

    for (IShape s: this.orderedShapes.keySet()) {
      if ((this.orderedShapes.get(s).get(0) >= tick) && (this.orderedShapes.get(s).get(1) <= tick)) {
        shapesAtTick.add(s);
      }
    }
    return shapesAtTick;
  }

  /**
   * Determines whether any of the shapes in the list of shapes for this animation
   * have the same name as the given string.
   *
   * @param name is the string of the shape name.
   * @return true if there is an overlap, false otherwise.
   */
  private boolean checkForDuplicateShapeNames(String name) {
    for (IShape s: this.shapes.values()) {
      if (s.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines the type of the action with the given starting and ending coordinates.
   *
   * @param startPointX is the starting x-coord.
   * @param startPointY is the starting y-coord.
   * @param endPointX is the ending x-coord.
   * @param endPointY is the ending y-coord.
   * @return the type of action this represents.
   */
  private ActionType determineActionType(int startPointX, int startPointY, int endPointX, int endPointY) {
    if ((startPointX == endPointX) && (startPointY == endPointY)) {
      return ActionType.STAY;
    } else {
      return ActionType.MOVE;
    }
  }
}
