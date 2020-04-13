package cs3500.animation.model;

import cs3500.animation.provider.view.IMotion;

import java.util.List;
import java.util.Arrays;

/**
 * Adapter class between ShapeAction abstract class and IMotion interface.
 */
public class ShapeActionToIMotionAdapter extends ShapeAction implements IMotion {
  /**
   * Constructor for a ShapeAction, sets all of the fields.
   *
   * @param startTick   is the starting tick for this action.
   * @param endTick     is the ending tick for this action.
   * @param startPoint  is the starting coordinate for this action.
   * @param endPoint    is the ending coordinate for this action.
   * @param startColor  is the starting color for the shape.
   * @param endColor    is the ending color for the shape.
   * @param startWidth
   * @param startHeight
   * @param endWidth
   * @param endHeight
   * @throws IllegalArgumentException if the starting or ending ticks are less than 0,
   *                                  or if the ending tick is less than the starting tick,
   *                                  or if the starting or ending coordinates are out of bounds.
   */
  public ShapeActionToIMotionAdapter(int startTick, int endTick, List<Integer> startPoint,
                                     List<Integer> endPoint, RGBColor startColor, RGBColor endColor,
                                     int startWidth, int startHeight, int endWidth, int endHeight) {
    super(startTick, endTick, startPoint, endPoint, startColor,
            endColor, startWidth, startHeight, endWidth, endHeight);
  }

  public ShapeActionToIMotionAdapter(IAction action) {
    super(action.getStartTick(), action.getEndTick(),
            Arrays.asList(action.getCoord("x", "start"), action.getCoord("y", "start")),
            Arrays.asList(action.getCoord("x", "end"), action.getCoord("y", "end")),
            action.getColor("start"), action.getColor("end"), action.getWidth("start"),
            action.getHeight("start"), action.getWidth("end"), action.getHeight("end"));
  }

  @Override
  public IAction returnCopy() {
    return new ShapeActionToIMotionAdapter(this);
  }

  @Override
  public ActionType getActionType() {
    if ((this.startPoint.get(0) == this.endPoint.get(0)) && (this.startPoint.get(1) == this.endPoint.get(1))) {
      return ActionType.STAY;
    } else {
      return ActionType.MOVE;
    }
  }
}
