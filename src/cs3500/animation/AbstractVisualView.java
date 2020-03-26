package cs3500.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a general animation view.
 */
public class AbstractVisualView extends JFrame implements AnimationView {
  private AnimationOperations model;
  private AnimationPanel panel;
  private JScrollPane scrollPane;
  private Timer timer;
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
    this.panel = new AnimationPanel(this.model);
    this.scrollPane = new JScrollPane(this.panel);
    this.add(this.scrollPane, BorderLayout.CENTER);
    timer = new Timer((1 / speed) * 1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        refresh();
      }
    });
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
  public String getTextualDescription() {
    return "";
  }

  @Override
  public void writeTextDescription(String fileName) {
    // Do nothing. This is only required for the TextView
  }

  @Override
  public void writeXML(String fileName, int speed) {
    // Do nothing. This is only required for the SVGView
  }
}
