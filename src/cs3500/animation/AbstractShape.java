package cs3500.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class to represent a shape. Has fields for the
 * actions associated with this shape and the color of this shape.
 */
public abstract class AbstractShape implements IShape {

  /**
   * Constructor for a shape, calls the setDimensions function from
   * the extended class.
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public AbstractShape(String name, ShapeType type) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.actions = new ArrayList<ShapeAction>();
    this.name = name;
    this.shapeType = type;
  }

  private String name;
  private List<ShapeAction> actions;
  protected ShapeType shapeType;

  /**
   * Returns a copy of the list of actions for this shape.
   *
   * @return a copy of the list of actions for this shape, empty list if this shape does not
   *         have any actions.
   */
  public List<ShapeAction> getActions() {
    List<ShapeAction> actions = new ArrayList<ShapeAction>();
    for (ShapeAction s: this.actions) {
      actions.add(s.returnCopy());
    }
    return actions;
  }

  @Override
  public IShape returnCopy() {
    return null;
  }

  @Override
  public void addShapeAction(ShapeAction action) {
    if (this.actions.size() == 0) {
      this.actions.add(action);
      return;
    }

    if (!this.actions.contains(action)) {
      for (ShapeAction a: this.actions) {
        if ((action.getStartTick() >= a.getStartTick())
                && (action.getStartTick() < a.getEndTick())) {
          throw new IllegalArgumentException("An action already exists in this time frame.");
        }
        if (action.getStartTick() == a.getEndTick()) {
          if ((action.getCoord("x", "start") != a.getCoord("x", "end"))
                  || (action.getCoord("y", "start") != a.getCoord("y", "end"))) {
            throw new IllegalArgumentException("Teleportation is not allowed in this animation.");
          }
        }
      }
      this.actions.add(action);
    }
  }


  @Override
  public String toString() {
    String out = "shape" + " " + this.name + " " + this.shapeType + "\n";

    if (this.actions.size() != 0) {
      for (ShapeAction a: this.actions) {
        out = out.concat(a.toString(this) + "\n");
      }
    }

    return out;
  }

  @Override
  public String getName() {
    return this.name;
  }



  @Override
  public boolean validateAction(ShapeAction action) {
    if (Objects.isNull(action)) {
      throw new IllegalArgumentException("The given action is null.");
    }
    for (ShapeAction a: this.actions) {
      if (a.hasOverlap(action)) {
        return false;
      }
      if (a.causesTeleportation(action)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof AbstractShape)) {
      return false;
    }
    AbstractShape s = (AbstractShape)other;
    if (s.actions.size() != this.actions.size()) {
      return false;
    }
    for (ShapeAction a:  this.actions) {
      if (!s.actions.contains(a)) {
        return false;
      }
    }
    return this.name.equals(s.name)
            && this.shapeType.equals(s.shapeType);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name,
            this.shapeType);
  }

  @Override
  public List<Integer> getPosition(int tick) {
    if (this.actions.size() == 0) {
      throw new IllegalArgumentException("This shape does not have an action at the"
              + "given tick.");
    }
    for (ShapeAction a: this.actions) {
      if ((tick >= a.startTick) && (tick <= a.endTick)) {
        if (a.getActionType().equals(ActionType.STAY)) {
          return a.startPoint;
        }
        else {
          double totalDistanceToTravel = Math.sqrt(Math.pow((a.getCoord("x",
                  "end") - a.getCoord("x", "start")), 2)
                  + Math.pow((a.getCoord("y", "end") - a.getCoord("y",
                  "start")), 2));
          double distancePerTick = totalDistanceToTravel / (a.getEndTick() - a.getStartTick());
          double distanceToTravelByTick = distancePerTick * (tick - a.getStartTick());
          double changeInX = a.getCoord("x", "end")
                  - a.getCoord("x", "start");
          double changeInY = a.getCoord("y", "end")
                  - a.getCoord("y", "start");
          double newX = a.getCoord("x", "start")
                  + (distanceToTravelByTick * Math.sqrt(1 / (1 + (changeInY / changeInX))));
          double newY = a.getCoord("y", "start")
                  + ((changeInY / changeInX) * distanceToTravelByTick
                  * Math.sqrt(1 / (1 + (changeInY / changeInX))));
          return Arrays.asList((int) Math.round(newX), (int) Math.round(newY));
        }
      }
    }
    throw new IllegalArgumentException("This shape does not have an action at the given tick.");
  }
}