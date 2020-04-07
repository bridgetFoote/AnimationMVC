package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.ShapeDrawParam;
import cs3500.animation.model.ShapeType;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
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
  private int finalTick;
  private boolean enableLooping = false;


  /**
   * Construct an AnimationPanel.
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
    if (this.enableLooping && (currentTick == this.finalTick)) {
      this.timer.restart();
      this.currentTick = 1;
    }
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

  /**
   * Updates the timer with the given speed value.
   *
   * @param speed is the new speed value to input into the timer.
   */
  public void updateTimer(int speed) {
    this.delay = 1000 / speed;
    this.timer.setDelay(this.delay);
  }

  /**
   * Pauses the timer.
   */
  public void pauseTimer() {
    this.timer.stop();
  }

  /**
   * Restarts the timer.
   */
  public void restartTimer() {
    this.timer.restart();
    this.currentTick = 1;
  }

  /**
   * Starts the timer from paused point.
   */
  public void playTimer() {
    this.timer.start();
  }

  /**
   * Sets the final tick of this animation so looping can be enabled.
   *
   * @param tick is the final tick of this animation.
   */
  public void setFinalTick(int tick) {
    this.finalTick = tick;
  }

  /**
   * Negates the value of enableLooping in respose to the button in the
   * view being toggled.
   */
  public void enableLoopingWasClicked() {
    this.enableLooping = !this.enableLooping;
  }
}
