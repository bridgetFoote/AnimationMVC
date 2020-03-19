package cs3500.animation;

import javax.swing.*;

/**
 * Represents an animation view displayed textually,
 * with each shape listed with their movements underneath organized by tick.
 */
public class TextView extends AbstractView {

  /**
   * Creates a text display animation view that displays this animation
   * in textual form.
   *
   * @param windowTitle is the title of the window.
   * @param readOnlyModel is the read only version of the model associated with
   *                      this animation.
   */
  public TextView(String windowTitle, AnimationOperations readOnlyModel) {
    super(windowTitle, readOnlyModel);

  }
}
