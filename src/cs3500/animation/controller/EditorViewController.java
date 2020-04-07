package cs3500.animation.controller;

import cs3500.animation.model.AnimationFrameOperations;
import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.IShape;
import cs3500.animation.view.AnimationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditorViewController implements IController, ActionListener {
  private AnimationView view;
  private AnimationFrameOperations model;

  public EditorViewController(AnimationFrameOperations model, AnimationView view) {
    if (!Objects.isNull(model) && !Objects.isNull(view)) {
      this.view = view;
      view.setFinalTick(model.getFinalTick());
      this.model = model;
    }
    else {
      throw new IllegalArgumentException("Model and view must not be null");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String speed = view.getNewTempo();
    String shapeString = view.getNewShape();
    String frameString = view.getFrameOperation();

    if (!speed.equals("")) {
      int newSpeed;
      try {
        newSpeed = Integer.parseInt(speed);
        view.updateTimerDelay(newSpeed);
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(new JDialog(), "Speed must be a positive integer");
      }
    }
    if (!shapeString.equals("")) {
      String[] shapeParams = shapeString.split(" ");
      if (shapeParams.length == 2) {
        this.model.addShape(shapeParams[0], shapeParams[1]);
      }
    }
    if (!frameString.equals("")) {
      String[] frameParams = frameString.split(" ");
      if (frameParams.length == 10) {
        try {
          String name = frameParams[0];
          int tick = Integer.parseInt(frameParams[1]);
          int xCoord = Integer.parseInt(frameParams[2]);
          int yCoord = Integer.parseInt(frameParams[3]);
          int width = Integer.parseInt(frameParams[4]);
          int height = Integer.parseInt(frameParams[5]);
          int redGradient = Integer.parseInt(frameParams[6]);
          int greenGradient = Integer.parseInt(frameParams[7]);
          int blueGradient = Integer.parseInt(frameParams[8]);
          String operation = frameParams[9];
          if (operation.equals("add")) {
            this.model.addKeyFrame(name, tick, xCoord, yCoord, width,
                    height, redGradient, greenGradient, blueGradient);
          } else if (operation.equals("remove")) {
            this.model.removeKeyFrame(name, tick);
          } else if (operation.equals("edit")) {
            this.model.editKeyFrame(name, tick, xCoord, yCoord, width,
                    height, redGradient, greenGradient, blueGradient);
          }
        } catch (NumberFormatException nfe) {
        }
      }
    }
  }

  @Override
  public void go() {
    this.view.setButtonListeners(this);
    this.view.makeVisible();
  }
}
