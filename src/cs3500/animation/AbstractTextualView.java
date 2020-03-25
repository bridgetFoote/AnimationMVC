package cs3500.animation;

import javax.naming.OperationNotSupportedException;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a textual view of an animation in either string or SVG form.
 */
public class AbstractTextualView implements AnimationView {

  private AnimationOperations model;

  /**
   * Creates a new textual view with the given window title and based on the
   * given model.
   *
   * @param readOnlyModel is the model to base the view off of.
   */
  public AbstractTextualView(AnimationOperations readOnlyModel) {
    if (Objects.isNull(readOnlyModel)) {
      throw new IllegalArgumentException("The read-only model can't be null.");
    }
    this.model = readOnlyModel;
  }

  @Override
  public void makeVisible() {
    // Do nothing, don't need to make anything visible.
  }

  @Override
  public String getTextualDescription() {
    String out = model.toString();
    Map<IShape, List<ShapeAction>> actions = model.getShapeActions();
    Set aKeys = actions.keySet();
    for (Object s: aKeys) {
      if (s instanceof IShape) {
        out = out.concat(s.toString());
      }
    }
    return out;
  }

  @Override
  public void writeTextDescription(String fileName) {
    // Do nothing, will be overridden by TextView
  }

  @Override
  public void writeXML(String fileName, int speed) {
    // Do nothing, will be overridden by SVGView
  }

}
