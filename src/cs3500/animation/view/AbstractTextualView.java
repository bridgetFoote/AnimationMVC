package cs3500.animation.view;

import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.IAction;
import cs3500.animation.model.IShape;
import cs3500.animation.model.ShapeAction;

import java.awt.event.ActionListener;
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
    throw new UnsupportedOperationException();
  }

  @Override
  public void setApplyNewSpeedButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNewTempo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getTextualDescription() {
    String out = model.toString();
    Map<IShape, List<IAction>> actions = model.getShapeActions();
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
