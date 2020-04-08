package cs3500.animation.view;

import cs3500.animation.controller.EditorViewController;
import cs3500.animation.controller.MouseHandler;
import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.IShape;
import cs3500.animation.model.ShapeType;
import cs3500.animation.model.ShapeWithKeyFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    addInput.setPreferredSize(new Dimension(200, 20));
    c.gridx = 6;
    c.gridwidth = 5;
    c.gridy = 1;
    speedInputPanel.add(addInput, c);

    this.applyAdd = new JButton("Add Shape");
    c.gridwidth = 1;
    c.gridx = 11;
    c.gridy = 1;
    speedInputPanel.add(applyAdd,c);

    JLabel addLabel = new JLabel("Enter new shape with format 'name' 'type'");
    c.gridx = 0;
    c.gridy = 1;
    speedInputPanel.add(addLabel,c);

    JFrame frameFrame = new JFrame("Frame Operations");
    JPanel frameOperationPanel = new JPanel(new GridBagLayout());

    this.frameInput = new JTextField("");
    frameInput.setPreferredSize(new Dimension(200, 20));
    c.gridx = 0;
    c.gridwidth = 5;
    c.gridy = 3;
    frameOperationPanel.add(frameInput, c);

    this.applyFrameOperation = new JButton("Apply Frame Operation");
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 4;
    frameOperationPanel.add(applyFrameOperation, c);

    JLabel addFrameLabelPart1 = new JLabel("Enter new or existing frame paramaters");
    c.gridx = 0;
    c.gridy = 0;
    frameOperationPanel.add(addFrameLabelPart1, c);

    JLabel addFrameLabelPart2 = new JLabel("and desired operation with format:");
    c.gridx = 0;
    c.gridy = 1;
    frameOperationPanel.add(addFrameLabelPart2);

    JLabel addFrameLabelPart3 = new JLabel("'shape name' 'tick' 'x coordinate' 'y coordinate'");
    c.gridx = 0;
    c.gridy = 2;
    frameOperationPanel.add(addFrameLabelPart3, c);

    JLabel addFrameLabelPart4 = new JLabel("'width' 'height' 'red' 'green' 'blue' 'add/remove/edit'");
    c.gridx = 0;
    c.gridy = 5;
    frameOperationPanel.add(addFrameLabelPart4);

    // add edit panels to view
    add(speedInputPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);
    frameFrame.add(frameOperationPanel, BorderLayout.EAST);
    frameFrame.setVisible(true);
    this.pack();
  }

  private JButton applyNewSpeed;
  private JButton applyAdd;
  private JButton applyFrameOperation;
  private JTextField addInput;
  private JTextField speedInput;
  private JTextField frameInput;

  @Override
  public void removeShape(String clickedShape) {
    this.panel.addDontDraw(clickedShape);
  }

  @Override
  public void addClickListener(EditorViewController listener) {
    MouseHandler m = new MouseHandler(listener, this);
    this.addMouseListener(m);
  }

  @Override
  public void setButtonListeners(ActionListener actionEvent) {
    this.applyNewSpeed.addActionListener(actionEvent);
    this.applyAdd.addActionListener(actionEvent);
    this.applyFrameOperation.addActionListener(actionEvent);
  }

  @Override
  public String getNewTempo() {
    String command = this.speedInput.getText();
    this.speedInput.setText("");
    return command;
  }

  @Override
  public String getNewShape() {
    String shapeString = this.addInput.getText();
    this.addInput.setText("");
    return shapeString;
  }

  @Override
  public String getFrameOperation() {
    String frameString = this.frameInput.getText();
    this.frameInput.setText("");
    return frameString;
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
