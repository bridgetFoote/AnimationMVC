package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Represents the panel to draw the animation on.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  private AnimationOperations model;
  private int currentTick;
  private int delay;
  private Timer timer;
  /**
   * Construct an AnimationPanel
   * @param m the model used for animation.
   */
  public AnimationPanel(AnimationOperations m, int speed) {
    if (Objects.isNull(m)) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = m;
    this.currentTick = 1;
    this.delay = 1000 / (speed); // Convert from ticks per second to time delay (ms)
    this.timer = new Timer(delay,this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    timer.start();
    for (ShapeDrawParam s: model.getShapesAtTick(currentTick)) {
      if (s.type.equals(ShapeType.RECTANGLE)) {
        g2d.setColor(new Color(s.color.getColorGradient("red"),
                s.color.getColorGradient("green"),
                s.color.getColorGradient("blue")));
        g2d.fillRect(s.xPosn,s.yPosn,s.width,s.height);
      }
      else {
        g2d.setColor(new Color(s.color.getColorGradient("red"),
                s.color.getColorGradient("green"),
                s.color.getColorGradient("blue")));
        g2d.fillOval(s.xPosn,s.yPosn,s.width,s.height);
      }
    }
    currentTick++;
  }

  /**
   * Re-draw for each animation.
   */
  public void remake() {
    if (this.model.getShapesAtTick(currentTick).size() > 0) {
      repaint();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      this.remake();
    }
  }
}
