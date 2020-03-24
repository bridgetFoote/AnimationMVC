package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

/**
 * Displays the shapes in this animation in the correct position,
 * and moving according to their defined actions at a set tempo.
 */
public class AnimationPanel extends JPanel {

  public AnimationPanel(AnimationOperations model) {
    super();
    this.setBackground(Color.white);
    if (Objects.isNull(model)) {
      throw new IllegalArgumentException("The model can't be null.");
    }
    this.model = model;
    this.xOffset = model.getXMin();
    this.yOffset = model.getYMin();
    this.tick = 0;
  }

  public static int xOffset;
  public static int yOffset;
  private AnimationOperations model;
  private int tick;


  @Override
  protected void paintComponent(Graphics g)  {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    List<IShape> shapes = this.model.getShapesAtTick(this.tick);

    for (IShape s: shapes) {
      Shape shape;
      if (s.getShapeType().equals("rect")) {
        shape = new Rectangle2D.Double(s.getPosition(this.tick).get(0),
                s.getPosition(this.tick).get(1), s.getWidth(this.tick), s.getHeight(this.tick));
        g2d.setPaint(new Color(s.getColorGradient("red", this.tick),
                s.getColorGradient("green", this.tick),
                s.getColorGradient("blue", this.tick)));
      } else {
        shape = new Ellipse2D.Double(s.getPosition(this.tick).get(0)
                - (s.getWidth(this.tick) / 2), s.getPosition(this.tick).get(1)
                - (s.getHeight(this.tick) / 2), s.getWidth(this.tick), s.getHeight(this.tick));
      }
      g2d.setPaint(new Color(s.getColorGradient("red", this.tick),
              s.getColorGradient("green", this.tick),
              s.getColorGradient("blue", this.tick)));
      g2d.fill(shape);
      g2d.draw(shape);
    }
  }

  protected void nextTick() {
    this.tick = this.tick + 1;
  }

}
