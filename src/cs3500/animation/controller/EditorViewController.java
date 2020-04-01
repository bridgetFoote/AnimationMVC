package cs3500.animation.controller;

import cs3500.animation.model.AnimationOperations;
import cs3500.animation.view.AnimationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditorViewController implements IController, ActionListener {
  private AnimationOperations model;
  private AnimationView view;

  public EditorViewController(AnimationOperations model, AnimationView view) {
    if (!Objects.isNull(model) && !Objects.isNull(view)) {
      this.model = model;
      this.view = view;
      view.setFinalTick(model.getFinalTick());
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String speed = view.getNewTempo();

    int newSpeed;
    try {
      newSpeed = Integer.parseInt(speed);
      view.updateTimerDelay(newSpeed);
    } catch (NumberFormatException nfe) {
    }
  }

  @Override
  public void go() {
    this.view.setApplyNewSpeedButtonListener(this);
    this.view.makeVisible();
  }
}
