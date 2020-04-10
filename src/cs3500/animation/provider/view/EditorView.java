package cs3500.animation.provider.view;

import cs3500.animation.model.KeyFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents an Editor's view of the ExCELlencemodel, where users have the ability to
 * pause and play the animation, and set the desired speed through clicking the corresponding
 * buttons. Users can also add and delete keyframes of each shape in the model.
 */
public class EditorView extends JFrame implements IView {
  private ExCELlenceOperationsReadOnly model;
  private int speed;

  private boolean playing;

  private EditorPanel panel;
  private Timer timer;

  private JButton pauseButton;
  private JButton restartButton;
  private JButton doubleSpeedButton;
  private JButton halfSpeedButton;
  private JCheckBox toggleLoop;

  private JButton addShapeButton;
  private JButton deleteShapeButton;
  private JButton addKeyframeButton;
  private JButton deleteKeyframeButton;
  private JButton modifyKeyframeButton;
  private JTextField userInput;

  private JList shapesKeyframesList;
  private DefaultListModel<String> shapesKeyframesListModel;


  /**
   * Constructor for the EditorView, taking in the title of the JFrame and the model
   * which it will pass on to the EditorPanel. Constructs all necessary
   * buttons, checkboxes, textfields, and JLists and sets the size of the window
   * depending on the parameters of the model which it was passed.
   * @param frameTitle the title of the window
   * @param model the model which the view will represent
   */
  public EditorView(String frameTitle, ExCELlenceOperationsReadOnly model) {
    super(frameTitle);
    this.model = model;
    panel = new EditorPanel(model);

    JScrollPane scrollPane;

    setSize(model.getWindowWidth() + 500, model.getWindowHeight() + 500);
    panel.setSize(model.getWindowWidth() + 100, model.getWindowHeight() + 100);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.speed = 1;

    scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER);

    this.playing = true;

    JPanel buttonPanel = new JPanel();
    JPanel editPanel = new JPanel();

    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause");

    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart");

    doubleSpeedButton = new JButton("2x");
    doubleSpeedButton.setActionCommand("Speed_Up");

    halfSpeedButton = new JButton(".5x");
    halfSpeedButton.setActionCommand("Speed_Down");

    addShapeButton = new JButton("Add Shape");
    addShapeButton.setActionCommand("Add_Shape");

    deleteShapeButton = new JButton("Delete Shape");
    deleteShapeButton.setActionCommand("Delete_Shape");
    deleteShapeButton.setEnabled(false);

    addKeyframeButton = new JButton("Add Keyframe");
    addKeyframeButton.setActionCommand("Add_Keyframe");

    deleteKeyframeButton = new JButton("Delete Keyframe");
    deleteKeyframeButton.setActionCommand("Delete_Keyframe");
    deleteKeyframeButton.setEnabled(false);

    modifyKeyframeButton = new JButton("Modify Keyframe");
    modifyKeyframeButton.setActionCommand("Modify_Keyframe");
    modifyKeyframeButton.setEnabled(false);

    userInput = new JTextField(25);

    buttonPanel.add(pauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(doubleSpeedButton);
    buttonPanel.add(halfSpeedButton);

    editPanel.add(userInput);
    editPanel.add(addShapeButton);
    editPanel.add(deleteShapeButton);
    editPanel.add(addKeyframeButton);
    editPanel.add(deleteKeyframeButton);
    editPanel.add(modifyKeyframeButton);

    toggleLoop = new JCheckBox("loop?");
    toggleLoop.setActionCommand("Loop");
    buttonPanel.add(toggleLoop);

    this.add(buttonPanel, BorderLayout.PAGE_START);
    this.add(editPanel, BorderLayout.SOUTH);

    this.shapesKeyframesList = new JList();
    shapesKeyframesListModel = new DefaultListModel();
    fillList(shapesKeyframesListModel);
    this.shapesKeyframesList.setModel(shapesKeyframesListModel);
    this.shapesKeyframesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    JScrollPane listScroller = new JScrollPane(shapesKeyframesList);
    this.add(listScroller, BorderLayout.EAST);

  }

  @Override
  public String text() {
    throw new UnsupportedOperationException("Method not used for this view");
  }

  @Override
  public void showView() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;

    timer = new Timer(1000 / speed, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.repaint();
      }
    });

    timer.start();
  }

  @Override
  public ExCELlenceOperationsReadOnly getModel() {
    throw new UnsupportedOperationException("Don't need to return model for editor view");
  }

  @Override
  public void setListener(ActionListener listener, ListSelectionListener listener2) {
    restartButton.addActionListener(listener);
    pauseButton.addActionListener(listener);
    doubleSpeedButton.addActionListener(listener);
    halfSpeedButton.addActionListener(listener);
    toggleLoop.addActionListener(listener);

    userInput.addActionListener(listener);
    addShapeButton.addActionListener(listener);
    deleteShapeButton.addActionListener(listener);
    addKeyframeButton.addActionListener(listener);
    deleteKeyframeButton.addActionListener(listener);
    modifyKeyframeButton.addActionListener(listener);

    shapesKeyframesList.addListSelectionListener(listener2);
  }


  /**
   * run when user presses pause button, either pausing or unpausing animation.
   */
  @Override
  public void pressPause() {
    if (playing) {
      this.timer.stop();
    }
    else {
      this.timer.start();
    }
    this.playing = !this.playing;
  }

  @Override
  public void pressRestart() {
    this.panel.reset();
  }

  @Override
  public void pressSpeedUp() {
    this.timer.setDelay(timer.getDelay() / 2);
  }

  @Override
  public void pressSlowDown() {
    this.timer.setDelay(timer.getDelay() * 2);
  }

  @Override
  public void pressLoop() {
    this.panel.isLooping = !this.panel.isLooping;
  }


  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getTick() {
    return panel.tick;
  }

  @Override
  public Timer getTimer() {
    return this.timer;
  }

  /**
   * Fills the given listmodel with all of view's model's shapes and keyframes.
   * @param model the model to be added to
   */
  private void fillList(DefaultListModel<String> model) {
    for (IShape s : this.model.getShapes().values()) {
      model.addElement(s.toString());
      for (KeyFrame k : s.getKeyframes()) {
        model.addElement(k.toString());
      }
    }
  }

  @Override
  public void updateModel(ExCELlenceOperationsReadOnly m) {
    this.model = m;
    this.shapesKeyframesListModel.clear();
    fillList(this.shapesKeyframesListModel);
  }

  @Override
  public JList getShapesKeyframesList() {
    return this.shapesKeyframesList;
  }

  @Override
  public JTextField getTextField() {
    return this.userInput;
  }

  @Override
  public JButton getDeleteShapeButton() {
    return this.deleteShapeButton;
  }

  @Override
  public JButton getDeleteKeyframeButton() {
    return this.deleteKeyframeButton;
  }

  @Override
  public JButton getModifyKeyframeButton() {
    return this.modifyKeyframeButton;
  }

  @Override
  public boolean isLooping() {
    return panel.isLooping;
  }
}
