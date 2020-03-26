package cs3500.animation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class to represent a shape action. Has fields for the starting and
 * ending ticks of the action as well as the starting and ending coordinates
 * for the shape.
 */
public abstract class ShapeAction implements IAction {

  /**
   * Constructor for a ShapeAction, sets all of the fields.
   * @param startTick is the starting tick for this action.
   * @param endTick is the ending tick for this action.
   * @param startPoint is the starting coordinate for this action.
   * @param endPoint is the ending coordinate for this action.
   * @param startColor is the starting color for the shape.
   * @param endColor is the ending color for the shape.
   * @throws IllegalArgumentException if the starting or ending ticks are less than 0,
   *                                  or if the ending tick is less than the starting tick,
   *                                  or if the starting or ending coordinates are out of bounds.
   */
  public ShapeAction(int startTick, int endTick, List<Integer> startPoint,
                     List<Integer> endPoint, RGBColor startColor,
                     RGBColor endColor, int startWidth, int startHeight, int endWidth, int endHeight) {
    if (startTick < 0) {
      throw new IllegalArgumentException("The starting time cannot be negative.");
    }
    if (endTick < 0) {
      throw new IllegalArgumentException("The ending time cannot be negative.");
    }
    if ((endTick - startTick) < 0) {
      throw new IllegalArgumentException("The ending time cannot be before the starting time.");
    }
    if (!(this.validateCoordinates(startPoint, endPoint))) {
      throw new IllegalArgumentException("The given coordinates are not valid.");
    }
    if (!this.validateTicks(startTick, endTick)) {
      throw new IllegalArgumentException("The given ticks are not valid.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.actionType = this.getActionType();
    this.startColor = startColor;
    this.endColor = endColor;
    if (endWidth <= 0 || startWidth <= 0) {
      throw new IllegalArgumentException("The width cannot be less than or equal to 0.");
    }
    this.endWidth = endWidth;
    this.startWidth = startWidth;
    if (endHeight <= 0 || startHeight <= 0) {
      throw new IllegalArgumentException("The height cannot be less than or equal to 0.");
    }
    this.endHeight = endHeight;
    this.startHeight = startHeight;
  }

  /**
   * Returns a copy of this shape action.
   *
   * @return a copy of this action.
   */
  abstract ShapeAction returnCopy();

  protected int startTick;
  protected int endTick;
  protected List<Integer> startPoint;
  protected List<Integer> endPoint;
  protected ActionType actionType;
  protected RGBColor startColor;
  protected RGBColor endColor;
  protected int startWidth;
  protected int startHeight;
  protected int endWidth;
  protected int endHeight;


  /**
   * Changes the timing of this action.
   * @param startTick is the new starting tick.
   * @param endTick is the new ending tick.
   * @throws IllegalArgumentException if the new starting and ending ticks are not valid.
   */
  public void changeActionTime(int startTick, int endTick) {
    if ((startTick < 0) || (endTick < 0) || ((endTick - startTick) < 0)) {
      throw new IllegalArgumentException("The given starting and ending times are invalid.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
  }


  /**
   * Determines whether the given ticks are valid.
   *
   * @param startTick is the start tick.
   * @param endTick is the end tick.
   * @return true if the ticks are valid, false if either one is
   *         negative or if the end tick is less than the start tick.
   */
  public boolean validateTicks(int startTick, int endTick) {
    if ((startTick < 0) || (endTick < 0) || (endTick < startTick)) {
      return false;
    }
    return true;
  }

  abstract ActionType getActionType();

  @Override
  public int hashCode() {
    return Objects.hash(this.startTick, this.endTick, this.startPoint,
            this.endPoint, this.actionType, this.startColor, this.endColor,
            this.endWidth, this.endHeight);
  }

  /**
   * Returns true if the given coordinates are valid for the type of ShapeAction.
   * @param startPoint is the start point.
   * @param endPoint if the end point.
   * @return true if the coordinates are valid
   * @throws IllegalArgumentException if the coordinates are not valid.
   */
  public boolean validateCoordinates(List<Integer> startPoint, List<Integer> endPoint) {
    if ((startPoint.size() != 2) || (endPoint.size() != 2)) {
      throw new IllegalArgumentException("The starting and ending coordinates "
              + "have too many components.");
    }
    /* TODO: fix this.
    if ((startPoint.get(0) < 0) || (startPoint.get(1) < 0)
            || (endPoint.get(0) < 0) || (endPoint.get(1) < 0)) {
      throw new IllegalArgumentException("The starting and ending coordinates "
              + "are out of bounds.");
    }*/
    return true;
  }

  /**
   * Renders this shape action as a string with the given shape.
   *
   * @param shape is the shape to attach this action to.
   * @return the string representing this action.
   */
  public String toString(AbstractShape shape) {
    String out = "motion" + " " + shape.getName() + " "
            + Integer.toString(this.startTick) + " " + Integer.toString(this.startPoint.get(0))
            + " " + Integer.toString(this.startPoint.get(1)) + " "
            + Integer.toString(this.startWidth)
            + " " + Integer.toString(this.startHeight) + " "
            + Integer.toString(this.startColor.getColorGradient("red"))
            + " " + Integer.toString(this.startColor.getColorGradient("green")) + " "
            + Integer.toString(this.startColor.getColorGradient("blue"))
            + "  " + Integer.toString(this.endTick) + " " + Integer.toString(this.endPoint.get(0))
            + " " + Integer.toString(this.endPoint.get(1)) + " "
            + Integer.toString(this.endWidth)
            + " " + Integer.toString(this.endHeight) + " "
            + Integer.toString(endColor.getColorGradient("red"))
            + " " + Integer.toString(endColor.getColorGradient("green")) + " "
            + Integer.toString(endColor.getColorGradient("blue"));

    return out;
  }

  /**
   * Returns the start tick for this action.
   *
   * @return the integer of the start tick for this action.
   */
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Returns the end tick for this action.
   *
   * @return the integer of the end tick for this action.
   */
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * Returns the specified start coordinate of this action.
   *
   * @param whichCoord is the coordinate to return, either x or y.
   * @return the specified coordinate.
   * @throws IllegalArgumentException if the coordinate type is not x or y,
   *                                  or the startOrEnd is not start or end.
   */
  public double getCoord(String whichCoord,  String startOrEnd) {
    switch (whichCoord) {
      case "x":
        if (startOrEnd.equals("start")) {
          return this.startPoint.get(0);
        } else if (startOrEnd.equals("end")) {
          return this.endPoint.get(0);
        } else {
          throw new IllegalArgumentException("Start or end point not specified.");
        }
      case "y":
        if (startOrEnd.equals("start")) {
          return this.startPoint.get(1);
        } else if (startOrEnd.equals("end")) {
          return this.endPoint.get(1);
        } else {
          throw new IllegalArgumentException("Start or end point not specified.");
        }
      default:
        throw new IllegalArgumentException("Invalid coord type.");
    }
  }

  /**
   * Determines whether the given action overlaps with this action.
   *
   * @param action is the action to compare to this action.
   * @return true if there is an overlap, false otherwise.
   */
  public boolean hasOverlap(ShapeAction action) {
    if (this.startTick > action.startTick) {
      return this.endTick < action.startTick;
    }
    else {
      return action.endTick < this.startTick;
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ShapeAction)) {
      return false;
    }

    ShapeAction a = (ShapeAction) other;

    return (this.startTick == a.startTick)
            && (this.endTick == a.endTick) && (this.startPoint.get(0) == a.startPoint.get(0))
            && (this.startPoint.get(1) == a.startPoint.get(1))
            && (this.endPoint.get(0) == a.endPoint.get(0))
            && (this.endPoint.get(1) == a.endPoint.get(1))
            && this.actionType.equals(a.actionType) && this.startColor.equals(a.startColor)
            && this.endColor.equals(a.endColor) && (this.endWidth == a.endWidth)
            && (this.endHeight == a.endHeight);
  }

  /**
   * Checks if adding the given action to a shape with this action in
   * its list of actions will cause teleportation.
   *
   * @param a the action to compare.
   * @return true if teleportation is inevitable, false otherwise.
   */
  public boolean causesTeleportation(ShapeAction a) {
    // TODO: Implement this...
    /*if (this.startTick == a.endTick) {
      return ((this.startPoint.get(0) != a.endPoint.get(0))
              || (this.startPoint.get(1) != a.endPoint.get(1)));
    } else if (this.endTick == a.startTick) {
      return ((this.endPoint.get(0) != a.startPoint.get(0))
              || (this.endPoint.get(1) != a.startPoint.get(1)));
    } else {
      return false;
    }*/
    return false;

  }

  /**
   * Returns the position of a shape executing this action at the given tick.
   *
   * @param tick is the tick to get the shape position at.
   * @return the position of the shape at said tick.
   * @throws IllegalArgumentException if the given tick is not within this action.
   */
  public List<Integer> getPosition(int tick) {
    if ((tick >= this.startTick) && (tick <= this.endTick)) {
      if (this.getActionType().equals(ActionType.STAY)) {
        return this.startPoint;
      } else {
        double totalDistanceToTravel = Math.sqrt(Math.pow((this.getCoord("x",
                "end") - this.getCoord("x", "start")), 2)
                + Math.pow((this.getCoord("y", "end") - this.getCoord("y",
                "start")), 2));
        double distancePerTick = totalDistanceToTravel / (this.getEndTick() - this.getStartTick());
        double distanceToTravelByTick = distancePerTick * (tick - this.getStartTick());
        double changeInX = this.getCoord("x", "end")
                - this.getCoord("x", "start");
        double changeInY = this.getCoord("y", "end")
                - this.getCoord("y", "start");
        double newX = this.getCoord("x", "start")
                + (distanceToTravelByTick * Math.sqrt(1 / (1 + (changeInY / changeInX))));
        double newY = this.getCoord("y", "start")
                + ((changeInY / changeInX) * distanceToTravelByTick
                * Math.sqrt(1 / (1 + (changeInY / changeInX))));
        return Arrays.asList((int) Math.round(newX), (int) Math.round(newY));
      }
    }
    throw new IllegalArgumentException("The given tick does not occur within this action.");
  }

}
