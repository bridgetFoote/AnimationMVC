package cs3500.animation.controller;


import cs3500.animation.view.EditorView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
  private EditorViewController controller;
  private EditorView view;

  /**
   * Construct a MouseHandler.
   * @param c the controller
   * @throws IllegalArgumentException if controller is null
   */
  public MouseHandler(EditorViewController c, EditorView v) {
    if (c == null) {
      throw new IllegalArgumentException("Controller can't be null");
    }
    this.controller = c;
    this.view = v;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int xPixel = e.getX();
    int yPixel = e.getY();
    controller.handleMouseClick(xPixel, yPixel);
  }
}
