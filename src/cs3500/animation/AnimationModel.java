package cs3500.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    shapes = new HashMap<String, SimpleShape>();
  }

  private HashMap<String, SimpleShape> shapes;

  @Override
  public String getTextDescription() {
    if (this.shapes.size() == 0) {
      return "";
    }

    String out = "";
    for (SimpleShape s: this.shapes.values()) {
      out = out.concat(s.toString() +  "\n");
    }

    return out;
  }

  @Override
  public void addShape(SimpleShape shape) {
    if (Objects.isNull(shape)) {
      throw new IllegalArgumentException("The shape to add can't be null.");
    }
    if (this.checkForDuplicateShapeNames(shape.getName())) {
      throw new IllegalArgumentException("A shape with this name already "
              + "exists in this animation.");
    }
    this.shapes.put(shape.getName(), shape);
  }

  @Override
  public void addShapeAction(SimpleShape shape, ShapeAction action) {
    if (Objects.isNull(shape) || Objects.isNull(action)) {
      throw new IllegalArgumentException("Neither the shape nor the action can be null.");
    }
    if (!this.shapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("The given shape does not exist in this animation.");
    }
    if (this.shapes.get(shape.getName()).validateAction(action)) {
      if (this.shapes.get(shape.getName()).getActions().size() == 0) {
        this.shapes.get(shape.getName()).addShapeAction(action);
      }
      else if (!action.causesOverlap(shape, this.getShapeActions())) {
        this.shapes.get(shape.getName()).addShapeAction(action);
      }
    }
  }

  @Override
  public List<SimpleShape> getShapes() {
    List<SimpleShape> list = new ArrayList<SimpleShape>();
    for (SimpleShape s: this.shapes.values()) {
      list.add(s.returnCopy());
    }
    return list;
  }

  @Override
  public HashMap<SimpleShape, List<ShapeAction>> getShapeActions() {
    HashMap<SimpleShape, List<ShapeAction>> map = new HashMap<SimpleShape, List<ShapeAction>>();

    for (SimpleShape s: this.shapes.values()) {
      map.put(s, s.getActions());
    }

    return map;
  }

  /**
   * Determines whether any of the shapes in the list of shapes for this animation
   * have the same name as the given string.
   *
   * @param name is the string of the shape name.
   * @return true if there is an overlap, false otherwise.
   */
  private boolean checkForDuplicateShapeNames(String name) {
    for (SimpleShape s: this.shapes.values()) {
      if (s.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }
}
