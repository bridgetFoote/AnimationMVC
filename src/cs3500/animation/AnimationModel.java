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
    orderedShapes = new TreeMap<Integer,List<ShapeDrawParam>>();
    // Default values for now
    topX = 0;
    topY = 0;
    canvasHeight = 0;
    canvasWidth = 0;
  }

  private HashMap<String, IShape> shapes;
  private TreeMap<Integer, List<ShapeDrawParam>> orderedShapes;
  private int topX;
  private int topY;
  private int canvasWidth;
  private int canvasHeight;


  @Override
  public String toString() {
    return String.format("canvas %d %d %d %d\n", this.topX, this.topY,
            this.canvasWidth, this.canvasHeight);
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Canvas width and height must be positive.");
    }
    this.topX = x;
    this.topY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
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
  public void addShapeAction(String shapeName, int startTick, int endTick, int startPointX,
                             int startPointY, int endPointX, int endPointY, int startRedGradient,
                             int startGreenGradient, int startBlueGradient, int endRedGradient,
                             int endGreenGradient, int endBlueGradient, int startWidth,
                             int startHeight, int endWidth, int endHeight) {
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
      this.shapes.get(shapeName).addShapeAction(action);
    }
  }

  @Override
  public void orderByTick() {
    int[] ticksActive;
    for (IShape s: shapes.values()) {
      ticksActive = this.findTicks(s);
      for (Integer t: ticksActive) {
        ShapeDrawParam newShape = this.findShapeParams(s,t);
        if (orderedShapes.containsKey(t))
        {
          List<ShapeDrawParam> cShapes = orderedShapes.get(t);
          cShapes.add(newShape);
          orderedShapes.put(t,cShapes);
        }
        else {
          List<ShapeDrawParam> nShapes = new ArrayList<>();
          nShapes.add(newShape);
          orderedShapes.put(t,nShapes);
        }
      }
    }
  }

  /**
   * Find the parameters needed to draw the shape. Do this using linear interpolation
   * to compute position, color, width, and height.
   * @param s the shape to be drawn
   * @param t the tick to draw the shape at
   * @return a {@link ShapeDrawParam} to be added to orderedShapes.
   */
  private ShapeDrawParam findShapeParams(IShape s, Integer t) {
   ShapeAction a = this.findCurrentAction(s, t);

   int newX = linearInterpolation(a.startTick, a.endTick, t, a.startPoint.get(0), a.endPoint.get(0));
   int newY = linearInterpolation(a.startTick, a.endTick, t, a.startPoint.get(1), a.endPoint.get(1));
  int newWidth = linearInterpolation(a.startTick, a.endTick, t, a.startWidth, a.endWidth);
  int newHeight = linearInterpolation(a.startTick, a.endTick, t, a.startHeight, a.endHeight);
  int newR = linearInterpolation(a.startTick, a.endTick, t, a.startColor.getColorGradient("red"),
          a.endColor.getColorGradient("red"));
  int newG = linearInterpolation(a.startTick, a.endTick, t, a.startColor.getColorGradient("green"),
          a.endColor.getColorGradient("green"));
  int newB = linearInterpolation(a.startTick, a.endTick, t, a.startColor.getColorGradient("blue"),
          a.endColor.getColorGradient("blue"));
  return new ShapeDrawParam(s.getType(), newX, newY, newWidth, newHeight, new RGBColor(newR, newG, newB));

  }


  /**
   * Perform linear interpolation to find the value at a given tick according to
   * f(t) = a(tb - t / tb - ta) + b (t - ta / tb - ta)
   * @param startTick ta
   * @param endTick tb
   * @param t t
   * @param startVal a
   * @param endVal b
   * @return f(t)
   */
  private int linearInterpolation(int startTick, int endTick, Integer t, int startVal, int endVal) {
    if (startTick == endTick) {
      return startVal;
    }
    return startVal * (endTick - t) / (endTick - startTick) + endVal * (t - startTick) / (endTick - startTick);
  }

  /**
   * Find the current action for the shape at the specified tick
   * @param s the shape
   * @param t the tick
   * @return the action occurring during this tick
   * @throws IllegalArgumentException if there is no action during this tick. 
   */
  private ShapeAction findCurrentAction(IShape s, Integer t) {
    for (ShapeAction a: s.getActions()) {
      if (a.startTick <= t && a.endTick >= t) {
        return a;
      }
    }
    throw new IllegalArgumentException("There is no action for the shape at this tick");
  }

  /**
   * For the given shape, find what ticks the shape is active (in the animation) for.
   * @param s the shape
   * @return an array of all ticks that the shape is active
   */
  private int[] findTicks(IShape s) {
    List<ShapeAction> actions = s.getActions();
    int start = actions.get(0).startTick;
    int end = actions.get(actions.size() - 1).endTick;
    int[] outArr = new int[end - start];
    int j = 0;
    for (int i = start; i < end; i++) {
      outArr[j] = i;
      j++;
    }
    return outArr;
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
  public List<ShapeDrawParam> getShapesAtTick(int tick) {
    if (this.orderedShapes.containsKey(tick)) {
      return this.orderedShapes.get(tick);
    }
    return new ArrayList<ShapeDrawParam>();
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
   * Return a copy of the model
   * @return a copy of the model
   * TODO: Might not need this!
   */
  public AnimationModel returnCopy() {
    AnimationModel returnThis = new AnimationModel();
    returnThis.topX = this.topX;
    returnThis.topY = this.topY;
    returnThis.canvasHeight = this.canvasHeight;
    returnThis.canvasWidth = this.canvasWidth;
    returnThis.shapes = this.shapes;
    returnThis.orderedShapes = this.orderedShapes;
    return returnThis;
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

  /**
   * Builds an Animation.
   */
  public static final class Builder implements AnimationBuilder<AnimationOperations> {
    private AnimationOperations model = new AnimationModel();

    @Override
    public AnimationOperations build() {
      this.model.orderByTick();
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationOperations> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> declareShape(String name, String type) {
      this.model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addMotion(String name, int t1, int x1, int y1,
                                                           int w1, int h1, int r1,
                                                           int g1, int b1, int t2,
                                                           int x2, int y2, int w2, int h2,
                                                           int r2, int g2, int b2) {
      this.model.addShapeAction(name, t1, t2, x1, y1, x2, y2 ,r1, g1, b1, r2, g2, b2, w1, h1, w2,h2);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addKeyframe(String name, int t, int x, int y,
                                                             int w, int h, int r, int g, int b) {
      // Apparently not needed for this assignment.
      return this;
    }
  }
}
