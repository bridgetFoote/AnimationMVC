package cs3500.animation;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Represents a textual view of an animation in either string or SVG form.
 */
public class AbstractTextualView extends JFrame implements AnimationView {

  /**
   * Creates a new textual view with the given window title and based on the
   * given model.
   *
   * @param windowTitle is the window title.
   * @param model is the model to base the view off of.
   */
  public AbstractTextualView(String windowTitle, AnimationOperations model, String outFile, int tempo) {
    super(windowTitle);
    if (Objects.isNull(model)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }
    if (outFile.equals("")) {
      throw new IllegalArgumentException("The output file is not valid.");
    }
    if (tempo <= 0) {
      throw new IllegalArgumentException("The tempo is not valid.");
    }

    this.outFile = outFile;
    this.tempo = tempo;
    this.model = model;

    setSize(400, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  protected String outFile;
  protected int tempo;
  protected AnimationOperations model;

  /**
   * Returns a text description of the movement and properties
   * of each shape in the animation over the entire duration
   * of time.
   *
   * @return string representing the animation.
   */
  protected String getTextDescription() {
    List<IShape> shapes = this.model.getShapes();
    if (shapes.size() == 0) {
      return "";
    }

    String out = "";
    for (IShape s: shapes) {
      out = out.concat(s.toString() +  "\n");
    }

    return out;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
