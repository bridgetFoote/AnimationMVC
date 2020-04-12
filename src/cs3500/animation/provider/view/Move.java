package cs3500.animation.provider.view;

import cs3500.animation.model.RGBColor;

import java.util.List;

public class Move extends cs3500.animation.model.Move implements IMotion {
  /**
   * Constructor for a ShapeAction, sets all of the fields.
   *
   * @param startTick   is the starting tick for this action.
   * @param endTick     is the ending tick for this action.
   * @param startPoint  is the starting coordinate for this action.
   * @param endPoint    is the ending coordinate for this action.
   * @param startColor
   * @param endColor
   * @param startWidth
   * @param startHeight
   * @param endWidth
   * @param endHeight
   * @throws IllegalArgumentException if the starting or ending ticks are less than 0,
   *                                  or if the ending tick is less than the starting tick,
   *                                  or if the starting or ending coordinates are out of bounds.
   */
  public Move(int startTick, int endTick, List<Integer> startPoint, List<Integer> endPoint, RGBColor startColor, RGBColor endColor, int startWidth, int startHeight, int endWidth, int endHeight) {
    super(startTick, endTick, startPoint, endPoint, startColor, endColor, startWidth, startHeight, endWidth, endHeight);
  }
}
