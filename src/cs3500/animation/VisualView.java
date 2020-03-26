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
  public VisualView(String windowTitle, AnimationOperations readOnlyModel, int speed) {
    super(windowTitle, readOnlyModel, speed);
  }
}
