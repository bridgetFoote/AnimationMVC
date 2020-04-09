package cs3500.animation.provider.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents a swing panel for the EditorView where the shapes must be drawn, and animations
 * can be edited to the user's liking.
 */
public class EditorPanel extends JPanel {
  protected ExCELlenceOperationsReadOnly model;
  protected int tick;
  protected boolean isLooping;


  /**
   * Constructor for an editor panel, taking in a read only version of a model and
   * using that model as the basis to draw the view out.
   * @param model read-only model
   */
  public EditorPanel(ExCELlenceOperationsReadOnly model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Panel must have a model");
    }

    this.tick = 0;
    this.model = model;
    this.setBackground(Color.WHITE);
    this.isLooping = false;
  }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    List<IShape> toDraw = this.model.getShapesAtKeyframe(tick);

    for (IShape s : toDraw) {
      g2d.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));

      if (s instanceof cs3500.animation.provider.view.Rectangle) {
        drawRect(g2d, (cs3500.animation.provider.view.Rectangle) s);
      }

      else {
        drawEllipse(g2d, (Ellipse) s);
      }

    }

    this.tick++;

    // If we have reached the last tick, which is the t2 of the final motion in the model
    // and loop box is checked, restart the animation
    int lastTick = 0;
    for (IShape s : this.model.getShapes().values()) {
      for (Keyframe k : s.getKeyframes()) {
        if (k.getTime() > lastTick) {
          lastTick = k.getTime();
        }
      }
    }

    if ((this.tick >= lastTick) && isLooping) {
      this.reset();
    }
  }

  /**
   * Resets the tick to 0, allowing the next repaint call to show the start of the animation.
   */
  protected void reset() {
    this.tick = 0;
  }

  /**
   * Draws a given rectangle onto a given 2D graphics context.
   * @param graphics the graphics object to be used
   * @param r the rectangle to be drawn
   */
  private void drawRect(Graphics2D graphics, cs3500.animation.provider.view.Rectangle r) {
    graphics.fillRect((int)r.getPosn().getX() - (int)model.getTopLeft().getX(),
            (int)r.getPosn().getY() - (int)model.getTopLeft().getY(),
            (int)r.getWidth(), (int)r.getHeight());
  }


  /**
   * Draws a given ellipse onto a given 2D graphics context.
   * @param graphics the graphics object to be used
   * @param e the ellipse to be drawn
   */
  private void drawEllipse(Graphics2D graphics, Ellipse e) {
    graphics.fillOval((int)e.getPosn().getX(), (int)e.getPosn().getY(),
            (int)e.getWidth(), (int)e.getHeight());
  }

}
