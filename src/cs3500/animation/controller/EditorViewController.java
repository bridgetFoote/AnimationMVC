package cs3500.animation.controller;

import cs3500.animation.model.AnimationOperations;
import cs3500.animation.view.AnimationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditorViewController implements IController, ActionListener {
  private AnimationView view;

  public EditorViewController(AnimationOperations model, AnimationView view) {
    if (!Objects.isNull(model) && !Objects.isNull(view)) {
      this.view = view;
      view.setFinalTick(model.getFinalTick());
    }
    else {
      throw new IllegalArgumentException("Model and view must not be null");
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
      JOptionPane.showMessageDialog(new JDialog(), "Speed must be a positive integer");
    }
  }

  @Override
  public void go() {
    this.view.setApplyNewSpeedButtonListener(this);
    this.view.makeVisible();
  }
}
