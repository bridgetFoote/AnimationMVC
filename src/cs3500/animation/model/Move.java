package cs3500.animation.model;

import java.util.List;

/**
 * Represents the move action, shape moves continuously over given interval
 * from one position to another in a straight line.
 */
public class Move extends ShapeAction {

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
  public Move(int startTick, int endTick, List<Integer> startPoint,
              List<Integer> endPoint, RGBColor startColor,
              RGBColor endColor, int startWidth, int startHeight, int endWidth, int endHeight) {
    super(startTick, endTick, startPoint, endPoint, startColor, endColor, startWidth, startHeight,
            endWidth, endHeight);
  }

  @Override
  public IAction returnCopy() {
    return new Move(this.startTick, this.endTick, this.startPoint, this.endPoint,
            this.startColor, this.endColor, this.startWidth, this.startHeight,
            this.endWidth, this.endHeight);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.MOVE;
  }
}
