package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;


import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a general animation view.
 */
public class AbstractVisualView extends JFrame implements AnimationView {
  private AnimationOperations model;
  protected AnimationPanel panel;

  /**
   * Creates a new visual view with the given window title and based off of the given model.
   *
   * @param windowTitle is the window title.
   * @param readOnlyModel is the model to base off of.
   */
  public AbstractVisualView(String windowTitle, AnimationOperations readOnlyModel, int speed) {
    super(windowTitle);
    if (Objects.isNull(readOnlyModel)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }
    this.model = readOnlyModel;
    List<Integer> canvas = this.getCanvas();
    setSize(canvas.get(2), canvas.get(3));
    setLocation(canvas.get(0), canvas.get(1));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(canvas.get(2) + canvas.get(0) + 200,
            canvas.get(3) + canvas.get(1) + 200));
    this.panel = new AnimationPanel(this.model, speed);
    add(this.panel, BorderLayout.CENTER);

    pack();
  }

  /**
   * Get the canvas details from the model.
   * @return a list containing the topX, topY, width, and height respectively.
   */
  private List<Integer> getCanvas() {
    String description = this.model.toString();
    String[] canvasString = description.split("\n")[0].split(" ");
    return Arrays.asList(Integer.parseInt(canvasString[1]), Integer.parseInt(canvasString[2]),
            Integer.parseInt(canvasString[3]), Integer.parseInt(canvasString[4]));
  }

  /**
   * Execute the animation.
   */
  public void refresh() {
    this.panel.remake();
  }



  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setButtonListeners(ActionListener actionEvent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getFrameOperation() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNewTempo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNewShape() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getTextualDescription() {
    return "";
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
  public void updateTimerDelay(int speed) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setFinalTick(int tick) {
    throw new UnsupportedOperationException();
  }
}
