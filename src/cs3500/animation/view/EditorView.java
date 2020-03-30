package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.AnimationPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents an animation view that displays the animation visually
 * and allows user functionality to pause, resume, restart, enable/disable
 * looping, and increase/decrease the speed of the animation.
 */
public class EditorView extends AbstractVisualView {

  public EditorView(String windowTitle, AnimationOperations readOnlyModel, int speed) {
    super(windowTitle, readOnlyModel, speed);

    // create button panel
    JPanel buttonPanel = new JPanel(new BorderLayout());

    // add start button
    JButton start = new JButton("Start");
    buttonPanel.add(start);

    // add pause button
    JButton pause = new JButton("Pause");
    JButton resume = new JButton("Resume");
    JButton restart = new JButton("Restart");
    JToggleButton enableDisable = new JToggleButton("Enable Looping");


    buttonPanel.add(pause);
    buttonPanel.add(resume);
    buttonPanel.add(restart);
    buttonPanel.add(enableDisable);
    JTextField speedInput = new JTextField("");
    JLabel speedLabel = new JLabel("Enter new animation speed in ticks per second.");
    JPanel speedInputPanel = new JPanel(new BorderLayout());
    speedInputPanel.add(speedInput, BorderLayout.CENTER);
    speedInputPanel.add(speedLabel, BorderLayout.WEST);
    JPanel editorPanel = new JPanel(new BorderLayout());
    editorPanel.add(buttonPanel, BorderLayout.NORTH);
    editorPanel.add(speedInputPanel, BorderLayout.SOUTH);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public String getTextualDescription() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void writeTextDescription(String fileName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void writeXML(String fileName, int speed) {
    throw new UnsupportedOperationException();
  }
}
