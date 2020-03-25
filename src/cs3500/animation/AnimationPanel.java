package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Represents the panel to draw the animation on.
 */
public class AnimationPanel extends JPanel {
  private AnimationOperations model;
  private int currentTick;

  /**
   * Construct an AnimationPanel
   * @param m the model used for animation.
   */
  public AnimationPanel(AnimationOperations m) {
    if (Objects.isNull(m)) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = m;
    this.currentTick = 0;
  }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (IShape s: model.getShapesAtTick(currentTick)) {
      // TODO: Draw the shape!

    }

    currentTick++;
  }

  /**
   * Re-draw for each animation.
   */
  public void remake() {
    if (model.getShapesAtTick(currentTick).size() != 0) {
      repaint();
    }
  }
}
