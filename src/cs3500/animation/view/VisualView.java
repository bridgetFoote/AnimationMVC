package cs3500.animation.view;

import cs3500.animation.AnimationOperations;
import cs3500.animation.view.AbstractVisualView;

/**
 * Represents an animation view displayed conventionally, with
 * the shapes moving through the view as they should.
 */
public class VisualView extends AbstractVisualView {
  public VisualView(String windowTitle, AnimationOperations readOnlyModel, int speed) {
    super(windowTitle, readOnlyModel,speed);
  }
}
