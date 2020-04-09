package cs3500.animation.provider.view;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a visual view of the ExCELlenceModel.
 */
public class VisualView extends JFrame implements IView {
  private ExcellencePanel panel;
  private int speed;



  /**
   * Helps construct a visualView for the ExCELlenceModel.
   *
   * @param frameTitle represents a given title of the frame.
   * @param model      represents a given ExCELlenceModel with ExCELlenceOperations.
   */
  public VisualView(String frameTitle, ExCELlenceOperationsReadOnly model) {
    super(frameTitle);
    panel = new ExcellencePanel(model);

    JScrollPane scrollPane;


    setSize(model.getWindowWidth() + 100, model.getWindowHeight() + 100);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.speed = 1;
    this.add(panel);

    scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.CENTER);



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

    Timer timer;

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
    throw new UnsupportedOperationException("Don't need to return model for visual view");
  }

  @Override
  public void setListener(ActionListener listener, ListSelectionListener listener2) {
    throw new UnsupportedOperationException("No listeners in this view");
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("No commands in this view");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("No Timer in this view");
  }

  @Override
  public void pressPause() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressRestart() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSpeedUp() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSlowDown() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressLoop() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void updateModel(ExCELlenceOperationsReadOnly m) {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JList getShapesKeyframesList() {
    throw new UnsupportedOperationException("No JList in this view");
  }

  @Override
  public JButton getDeleteShapeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getDeleteKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getModifyKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JTextField getTextField() {
    throw new UnsupportedOperationException("No JTextField in this view");
  }

  @Override
  public boolean isLooping() {
    throw new UnsupportedOperationException("Looping not used in this view.");
  }
}
