package cs3500.animation;

import java.util.List;

/**
 * Represents the stay action, shape stays in the same place from initial to final tick.
 * This is the method to change color or shape size over time, without changing the shape
 * position.
 */
public class Stay extends ShapeAction {

  /**
   * Constructor for a ShapeAction, sets all of the fields.
   *
   * @param startTick  is the starting tick for this action.
   * @param endTick    is the ending tick for this action.
   * @param startPoint is the starting coordinate for this action.
   * @param endPoint   is the ending coordinate for this action.
   * @throws IllegalArgumentException if the starting or ending ticks are less than 0,
   *                                  or if the ending tick is less than the starting tick,
   *                                  or if the starting or ending coordinates are out of bounds.
   */
  public Stay(int startTick, int endTick, List<Integer> startPoint,
              List<Integer> endPoint, RGBColor startColor,
              RGBColor endColor, int endWidth, int endHeight) {
    super(startTick, endTick, startPoint, endPoint, startColor, endColor, endWidth, endHeight);
    if ((startPoint.get(0) != endPoint.get(0)) || (startPoint.get(1) != endPoint.get(1))) {
      throw new IllegalArgumentException("The starting position is not the same "
              + "as the ending position.");
    }
  }

  @Override
  ShapeAction returnCopy() {
    return new Stay(this.startTick, this.endTick, this.startPoint, this.endPoint,
            this.startColor, this.endColor, this.endWidth, this.endHeight);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.STAY;
  }

  @Override
  public boolean validateCoordinates(List<Integer> startPoint, List<Integer> endPoint) {
    return !((startPoint.get(0) != endPoint.get(0)) || (startPoint.get(1) != endPoint.get(1)));
  }
}
