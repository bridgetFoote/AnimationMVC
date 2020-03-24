package cs3500.animation;

import javax.swing.*;

/**
 * Represents an animation view displayed textually,
 * with each shape listed with their movements underneath organized by tick.
 */
public class TextView extends AbstractTextualView {

  /**
   * Creates a text display animation view that displays this animation
   * in textual form.
   *
   * @param windowTitle is the title of the window.
   * @param readOnlyModel is the read only version of the model associated with
   *                      this animation.
   * @param outFile is the path to the output file.
   * @param tempo is the ticks per second speed of the animation.
   */
  public TextView(String windowTitle, AnimationOperations readOnlyModel, String outFile, int tempo) {
    super(windowTitle, readOnlyModel, outFile, tempo);
    JLabel label = new JLabel(this.getTextDescription());
    this.add(label);
    this.setVisible(true);
  }
}
