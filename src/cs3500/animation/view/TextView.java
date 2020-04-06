package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an animation view displayed textually,
 * with each shape listed with their movements underneath organized by tick.
 */
public class TextView extends AbstractTextualView {

  /**
   * Creates a text display animation view that displays this animation
   * in textual form.
   *
   * @param readOnlyModel is the read only version of the model associated with
   *                      this animation.
   */
  public TextView(AnimationOperations readOnlyModel) {
    super(readOnlyModel);
  }

  @Override
  public void writeTextDescription(String fileName) {
    String description = this.getTextualDescription();
    if (fileName.equals("")) {
      System.out.println(description);
      return;
    }
    try {
      FileWriter writer = new FileWriter(String.format("resources/%s", fileName),
              false);
      writer.write(description);
      writer.close();
    }
    catch (IOException e) {
      // Do nothing.
    }
  }
}
