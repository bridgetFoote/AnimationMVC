package cs3500.animation;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents a textual view of an animation in either string or SVG form.
 */
public class AbstractTextualView extends JFrame implements AnimationView {

  /**
   * Creates a new textual view with the given window title and based on the
   * given model.
   *
   * @param windowTitle is the window title.
   * @param readOnlyModel is the model to base the view off of.
   */
  public AbstractTextualView(String windowTitle, AnimationOperations readOnlyModel) {
    super(windowTitle);
    if (Objects.isNull(readOnlyModel)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }

    setSize(400, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
