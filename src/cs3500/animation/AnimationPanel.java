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
  Timer timer = new Timer(100,this);
  int LAST_TICK = 99;

  /**
   * Construct an AnimationPanel
   * @param m the model used for animation.
   */
  public AnimationPanel(AnimationOperations m) {
    if (Objects.isNull(m)) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = m;
    this.currentTick = 1;
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
    if (currentTick < LAST_TICK) {
      currentTick++;
    }
  }



  /**
   * Find the action to perform for the shape at the current tick.
   * @param s the shape.
   * @return the action that is to be performed during this tick
   * @throws IllegalStateException if the action doesn't occur during this tick.
   */
  private ShapeAction findAction(IShape s) {
    for (ShapeAction a: s.getActions()) {
      if (a.startTick <= currentTick && a.endTick >= currentTick) {
        return a;
      }
    }
    throw new IllegalStateException("Shape should not be present during this tick.");
  }

  /**
   * Re-draw for each animation.
   */
  public void remake() {
    repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      this.remake();
    }
  }
}
