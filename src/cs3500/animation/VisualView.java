package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents an animation view displayed conventionally, with
 * the shapes moving through the view as they should.
 */
public class VisualView extends AbstractVisualView {
  public VisualView(String windowTitle, AnimationOperations readOnlyModel, int tempo) {
    super(windowTitle, readOnlyModel, tempo);
    this.panel = new AnimationPanel(readOnlyModel);
    this.panel.setPreferredSize(new Dimension(readOnlyModel.getCanvasWidth(), readOnlyModel.getCanvasHeight()));
    this.scrollPane = new JScrollPane(this.panel);
    this.add(this.scrollPane, BorderLayout.CENTER);
    timer = new Timer((1 / tempo) * 1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.nextTick();
        refresh();
      }
    });
    timer.start();
    this.pack();
  }

  private Timer timer;
  private AnimationPanel panel;
  private JScrollPane scrollPane;

  @Override
  public void refresh() {
    this.repaint();
  }
}
