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

    // add speed input field
    this.speedInput = new JTextField("");
    this.applyNewSpeed = new JButton("Apply");
    JLabel speedLabel = new JLabel("Enter new animation speed in ticks per second.");
    JPanel speedInputPanel = new JPanel(new BorderLayout());

    // create speed input panel
    speedInputPanel.add(speedInput, BorderLayout.CENTER);
    speedInputPanel.add(speedLabel, BorderLayout.WEST);
    speedInputPanel.add(applyNewSpeed, BorderLayout.EAST);

    // add edit panels to view
    add(speedInputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);

    this.pack();
  }

  private JButton applyNewSpeed;
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
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
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
