package cs3500.animation.model;

import java.util.*;

/**
 * Abstract class to represent a shape. Has fields for the
 * actions associated with this shape and the color of this shape.
 */
public class Shape implements IShape {

  /**
   * Constructor for a shape, calls the setDimensions function from
   * the extended class.
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public Shape(String name, ShapeType type) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    if (Objects.isNull(type)) {
      throw new IllegalArgumentException("Shape type cannot be null");
    }
    this.actions = new ArrayList<IAction>();
    this.name = name;
    this.shapeType = type;
  }

  protected String name;
  protected List<IAction> actions;
  protected ShapeType shapeType;



  /**
   * Returns a copy of the list of actions for this shape.
   * @return a copy of the list of actions for this shape, empty list if this shape does not
   *         have any actions.
   */
  public List<IAction> getActions() {
    List<IAction> actions = new ArrayList<IAction>();
    for (IAction a: this.actions) {
      actions.add(a.returnCopy());
    }
    return actions;
  }


  @Override
  public void addShapeAction(IAction action) {
    if (this.actions.size() == 0) {
      this.actions.add(action);
      return;
    }

    if (!this.actions.contains(action)) {
      for (IAction a: this.actions) {
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
      for (IAction a: this.actions) {
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
  public ShapeType getType() {
    return this.shapeType;
  }

  @Override
  public boolean validateAction(IAction action) {
    if (Objects.isNull(action)) {
      throw new IllegalArgumentException("The given action is null.");
    }
    if (this.causesGap(action)) {
      return false;
    }
    for (IAction a: this.actions) {
      if (a.hasOverlap(action)) {
        return false;
      }
      if (a.causesTeleportation(action)) {
        return false;
      }

    }
    return true;
  }

  /**
   * Helper method to determine if this action will cause a gap in the animation.
   * N.B. This assumes that actions are only being added to the end and that files
   *      have each action ordered by occurrence. This also assumes that no overlap
   *      occurs between animations.
   * @param action the action.
   * @return whether the action will cause a gap in the animation
   */
  protected boolean causesGap(IAction action) {
    if (actions.size() == 0) {
      return false;
    }
    IAction lastAction = this.actions.get(actions.size() - 1);
    return action.getStartTick() != lastAction.getEndTick();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Shape)) {
      return false;
    }
    Shape s = (Shape)other;
    if (s.actions.size() != this.actions.size()) {
      return false;
    }
    for (IAction a:  this.actions) {
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
    for (IAction a: this.actions) {
      if ((tick >= a.getStartTick()) && (tick <= a.getEndTick())) {
        if (a.getActionType().equals(ActionType.STAY)) {
          return Arrays.asList(a.getCoord("x", "start"), a.getCoord("y", "start"));
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

  public class CompareActions implements Comparator<IAction> {

    @Override
    public int compare(IAction o1, IAction o2) {
      return Integer.compare(o1.getStartTick(), o2.getStartTick());
    }
  }

}
