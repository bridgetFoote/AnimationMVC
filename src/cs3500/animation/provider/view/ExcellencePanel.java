package cs3500.animation.provider.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents a swing panel for the visualView where the shapes must be drawn.
 */
public class ExcellencePanel extends JPanel {
  protected ExCELlenceOperationsReadOnly model;
  protected int tick;

  /**
   * Constructor for visual view panel.
   * @param model the model that this panel will draw
   */
  public ExcellencePanel(ExCELlenceOperationsReadOnly model) {
    super();

    if (model == null) {
      throw new IllegalArgumentException("Panel must have a model");
    }

    this.tick = 0;
    this.model = model;
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    List<IShape> toDraw = this.model.getShapesAtKeyframe(tick);

    for (IShape s : toDraw) {
      g2d.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));

      if (s instanceof Rectangle) {
        drawRect(g2d, (Rectangle) s);
      }

      else {
        drawEllipse(g2d, (Ellipse) s);
      }

    }

    this.tick++;
  }

  /**
   * Draws a given rectangle onto a given 2D graphics context.
   * @param graphics the graphics object to be used
   * @param r the rectangle to be drawn
   */
  private void drawRect(Graphics2D graphics, Rectangle r) {
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
