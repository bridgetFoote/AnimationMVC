package cs3500.animation;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents a general animation view.
 */
public class AbstractVisualView extends JFrame implements AnimationView {

  /**
   * Creates a new visual view with the given window title and based off of the given model.
   *
   * @param windowTitle is the window title.
   * @param readOnlyModel is the model to base off of.
   */
  public AbstractVisualView(String windowTitle, AnimationOperations readOnlyModel, int tempo) {
    super(windowTitle);
    if (Objects.isNull(readOnlyModel)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }
    if (tempo <= 0) {
      throw new IllegalArgumentException("The tempo is not valid.");
    }

    this.tempo = tempo;
    this.readOnlyModel = readOnlyModel;

    setSize(400, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private int tempo;
  private AnimationOperations readOnlyModel;

  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
