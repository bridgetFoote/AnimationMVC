package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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
    if (Objects.isNull(model.getShapesAtTick(currentTick))) {
      for (IShape s: model.getShapesAtTick(currentTick)) {
        Shape shape;
        if (s.getShapeType().str.equals("rectangle")) {
          shape = new Rectangle2D.Double(s.getPosition(this.currentTick).get(0),
                  s.getPosition(this.currentTick).get(1), s.getWidth(this.currentTick), s.getHeight(this.currentTick));
          g2d.setPaint(new Color(s.getColorGradient(this.currentTick, "red"),
                  s.getColorGradient(this.currentTick, "green"),
                  s.getColorGradient(this.currentTick, "blue")));
        } else {
          shape = new Ellipse2D.Double(s.getPosition(this.currentTick).get(0)
                  - (s.getWidth(this.currentTick) / 2), s.getPosition(this.currentTick).get(1)
                  - (s.getHeight(this.currentTick) / 2), s.getWidth(this.currentTick), s.getHeight(this.currentTick));
        }
        g2d.setPaint(new Color(s.getColorGradient(this.currentTick, "red"),
                s.getColorGradient(this.currentTick, "green"),
                s.getColorGradient(this.currentTick, "blue")));
        g2d.fill(shape);
        g2d.draw(shape);

      }
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
