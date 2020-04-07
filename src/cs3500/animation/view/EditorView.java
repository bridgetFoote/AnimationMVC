package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents an animation view that displays the animation visually
 * and allows user functionality to pause, resume, restart, enable/disable
 * looping, and increase/decrease the speed of the animation.
 */
public class EditorView extends AbstractVisualView {

  public EditorView(String windowTitle, AnimationOperations readOnlyModel, int speed) {
    super(windowTitle, readOnlyModel, speed);

    // create button panel
    JPanel buttonPanel = new JPanel(new FlowLayout());

    // add start button
    JButton start = new JButton("Start");
    start.addActionListener((ActionEvent e) -> {
      this.panel.playTimer();
    });
    buttonPanel.add(start);

    // add pause button
    JButton pause = new JButton("Pause");
    pause.addActionListener((ActionEvent e) -> {
      this.panel.pauseTimer();
    });
    buttonPanel.add(pause);

    // add resume button
    JButton resume = new JButton("Resume");
    resume.addActionListener((ActionEvent e) -> {
      this.panel.playTimer();
    });
    buttonPanel.add(resume);

    // add restart button
    JButton restart = new JButton("Restart");
    restart.addActionListener((ActionEvent e) -> {
      this.panel.restartTimer();
    });
    buttonPanel.add(restart);

    // add looping button
    JToggleButton enableDisableLooping = new JToggleButton("Enable Looping");
    enableDisableLooping.addActionListener((ActionEvent e) -> {
      this.panel.enableLoopingWasClicked();
    });
    buttonPanel.add(enableDisableLooping);

    GridBagConstraints c = new GridBagConstraints();
    JPanel speedInputPanel = new JPanel(new GridBagLayout());

    this.speedInput = new JTextField("");
    speedInput.setPreferredSize(new Dimension(80, 20));
    c.gridx = 6;
    c.gridwidth = 5;
    c.gridy = 0;
    speedInputPanel.add(speedInput, c);

    this.applyNewSpeed = new JButton("Apply");
    c.gridwidth = 1;
    c.gridx = 11;
    c.gridy = 0;
    speedInputPanel.add(applyNewSpeed, c);

    JLabel speedLabel = new JLabel("Enter new animation speed in ticks per second.");
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 0;
    speedInputPanel.add(speedLabel, c);

    this.addInput = new JTextField("");
    addInput.setPreferredSize(new Dimension(80, 20));
    c.gridx = 6;
    c.gridwidth = 5;
    c.gridy = 1;
    speedInputPanel.add(addInput, c);

    this.applyAdd = new JButton("Add Shape");
    c.gridwidth = 1;
    c.gridx = 11;
    c.gridy = 1;
    speedInputPanel.add(applyAdd,c);

    JLabel addLabel = new JLabel("Choose a shape to add to the animation");
    c.gridx = 0;
    c.gridy = 1;
    speedInputPanel.add(addLabel,c);

    // add edit panels to view
    add(speedInputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);

    this.pack();
  }

  private JButton applyNewSpeed;
  private JButton applyAdd;
  private JTextField addInput;
  private JTextField speedInput;

  @Override
  public void setApplyNewSpeedButtonListener(ActionListener actionEvent) {
    this.applyNewSpeed.addActionListener(actionEvent);
  }

  @Override
  public String getNewTempo() {
    String command = this.speedInput.getText();
    this.speedInput.setText("");
    return command;
  }

  @Override
  public void updateTimerDelay(int speed) {
    this.panel.updateTimer(speed);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
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

  @Override
  public void setFinalTick(int tick) {
    this.panel.setFinalTick(tick);
  }
}
