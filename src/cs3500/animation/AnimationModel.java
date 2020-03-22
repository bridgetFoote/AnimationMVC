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


  @Override
  public void addShape(int redGradient, int greenGradient, int blueGradient,
                       int width, int height, String name, String shapeType) {
    IShape shape;
    if (ShapeType.RECTANGLE.str.equals(shapeType)) {
      shape = new Rectangle(new RGBColor(redGradient, greenGradient, blueGradient), width, height, name);
    } else if (ShapeType.ELLIPSE.str.equals(shapeType)) {
      shape = new Ellipse(new RGBColor(redGradient, greenGradient, blueGradient), width, height, name);
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
                             int endGreenGradient, int endBlueGradient, int endWidth,
                             int endHeight) {
    if (!this.shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("The given shape does not exist in this animation.");
    }
    ShapeAction action;
    if (this.determineActionType(startPointX, startPointY, endPointX, endPointY).equals(ActionType.MOVE)) {
      action = new Move(startTick, endTick, Arrays.asList(startPointX, startPointY),
              Arrays.asList(endPointX, endPointY), new RGBColor(startRedGradient, startGreenGradient,
              startBlueGradient), new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
              endWidth, endHeight);
    } else {
      action = new Stay(startTick, endTick, Arrays.asList(startPointX, startPointY),
              Arrays.asList(endPointX, endPointY), new RGBColor(startRedGradient, startGreenGradient,
              startBlueGradient), new RGBColor(endRedGradient, endGreenGradient, endBlueGradient),
              endWidth, endHeight);
    }


    if (this.shapes.get(shapeName).validateAction(action)) {
      if (this.shapes.get(shapeName).getActions().size() == 0) {
        this.shapes.get(shapeName).addShapeAction(action);
      }
    }
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
