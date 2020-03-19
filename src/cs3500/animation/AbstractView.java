package cs3500.animation;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents a general animation view.
 */
public class AbstractView extends JFrame implements AnimationView {

  public AbstractView(String windowTitle, AnimationOperations readOnlyModel) {
    super(windowTitle);
    if (Objects.isNull(readOnlyModel)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }

    setSize(400, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new AnimationPanel(readOnlyModel);
    this.add(this.panel);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
