package cs3500.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class to represent a shape. Has fields for the
 * actions associated with this shape and the color of this shape.
 */
public abstract class SimpleShape {

  /**
   * Constructor for a shape, calls the setDimensions function from
   * the extended class.
   * @param rGBColor is the color of the shape.
   * @param width is the width for this shape.
   * @param height is the height for this shape.
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if any of the parameters are invalid.
   */
  public SimpleShape(RGBColor rGBColor, double width, double height, String name) {
    this.actions = new ArrayList<ShapeAction>();
    this.rGBColor = rGBColor;
    if (name.equals("")) {
      throw new IllegalArgumentException("The name of this shape cannot be the empty string.");
    }
    this.name = name;
    if (width <= 0) {
      throw new IllegalArgumentException("The length cannot be less than or equal to zero.");
    }
    this.width = width;
    if (height <= 0) {
      throw new IllegalArgumentException("The width cannot be less than or equal to zero.");
    }
    this.height = height;
    this.setShapeType();
  }

  private List<ShapeAction> actions;
  private RGBColor rGBColor;
  private String name;
  protected String shapeType;
  private double width;
  private double height;

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

  /**
   * Returns a copy of this shape.
   *
   * @return a copy of this shape.
   */
  abstract SimpleShape returnCopy();

  /**
   * Sets the shape type for this shape.
   */
  abstract void setShapeType();

  /**
   * Adds an action to this shape's list of actions if it does
   * not already exist.
   * @param action is the ShapeAction to add.
   * @throws IllegalArgumentException if the given shape action overlaps with
   *                                  a shape action already in the list.
   */
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

  /**
   * Removes an action from this shape's list of actions if it exists.
   * @param action is the ShapeAction to remove.
   */
  public void removeShapeAction(ShapeAction action) {
    if (this.actions.contains(action)) {
      this.actions.remove(action);
    }
  }

  /**
   * Gets the width of this shape.
   *
   * @return the double value for the width of this shape.
   */
  public double getShapeWidth() {
    return this.width;
  }

  /**
   * Gets the height of this shape.
   *
   * @return  the double value for the height of this shape.
   */
  public double getShapeHeight() {
    return this.height;
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

  /**
   * Returns the name of this shape.
   * @return the string of the name of this shape.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the value for the given color gradient.
   *
   * @param gradientType is the gradient type to return.
   * @return integer value of the requested gradient type.
   * @throws IllegalArgumentException if the gradient type is not valid.
   */
  public int getColorGradient(String gradientType) {
    return this.rGBColor.getColorGradient(gradientType);
  }

  /**
   * Determines whether the given action can be added to this shape.
   *
   * @param action is the action to validate.
   * @return true if it can be added, false otherwise.
   * @throws IllegalArgumentException is the action is null.
   */
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
    if (!(other instanceof SimpleShape)) {
      return false;
    }
    SimpleShape s = (SimpleShape)other;
    if (s.actions.size() != this.actions.size()) {
      return false;
    }
    for (ShapeAction a:  this.actions) {
      if (!s.actions.contains(a)) {
        return false;
      }
    }
    return this.rGBColor.equals(s.rGBColor)
            && this.name.equals(s.name)
            && this.shapeType.equals(s.shapeType)
            && (this.width == s.width)
            && (this.height == s.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actions, this.rGBColor, this.name,
            this.shapeType, this.width, this.height);
  }

  /**
   * Returns the position of this shape at the given tick.
   *
   * @param tick is the tick at which to check this shape's location.
   * @return the list of integers representing the shape's position.
   * @throws IllegalArgumentException if the shape does not have an action at the tick.
   */
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
