package cs3500.animation.provider.view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;


/**
 * Represents the view interface for the animator, and holds methods that all views
 * should implement.
 */
public interface IView {

  /**
   * Produces textual view of the model for utility and output.
   */
  String text();

  /**
   * Shows the view to the user.
   */
  void showView() throws IOException;

  /**
   * Repaints the view and is triggered at varying times depending on how fast user
   * wants animation to be shown.
   */
  void refresh();

  /**
   * Sets the speed of the animation.
   * @param speed the desired speed
   */
  void setSpeed(int speed);

  /**
   * Returns the read-only model of the view.
   * @return the model
   */
  ExCELlenceOperationsReadOnly getModel();


  /**
   * Sets the listener of all buttons in the view to the given listeners.
   * @param listener the ActionListener listening to button clicks
   * @param listener2 the ListSelectionListener listening to changes in the JList
   */
  void setListener(ActionListener listener, ListSelectionListener listener2);


  /**
   * Helps get the current speed of the animation.
   *
   * @return an integer representing the speed of the animation.
   */
  int getSpeed();

  /**
   * Helps get the current tick of the animation.
   *
   * @return an integer representing the current tick.
   */
  int getTick();

  /**
   * Gets the timer from a view to keep track of the current time.
   *
   * @return a Timer that the view follows.
   */
  Timer getTimer();

  /**
   * run when user presses pause button, either pausing or unpausing animation.
   */
  void pressPause();

  /**
   * run when user presses restart button, restarting animation.
   */
  void pressRestart();

  /**
   * run when user presses speed up button.
   */
  void pressSpeedUp();

  /**
   * run when user presses slow down button.
   */
  void pressSlowDown();

  /**
   * run when user presses loop button.
   */
  void pressLoop();

  /**
   * Updates model to same as controllers after controller makes changes to its model.
   * @param m the new model
   */
  void updateModel(ExCELlenceOperationsReadOnly m);

  /**
   * Gets the view's list of shapes and keyframes.
   * @return the Jlist
   */
  JList getShapesKeyframesList();


  /**
   * gets the view's user input text field.
   * @return the JTextField
   */
  JTextField getTextField();

  /**
   * gets the view's delete shape button.
   * @return the JButton
   */
  JButton getDeleteShapeButton();

  /**
   * gets the view's delete shape button.
   * @return the JButton
   */
  JButton getDeleteKeyframeButton();

  /**
   * gets the view's delete shape button.
   * @return the JButton
   */
  JButton getModifyKeyframeButton();

  /**
   * Indicates whether a view has a looping animation.
   * @return an boolean, true if looping false otherwise.
   */
  boolean isLooping();


}
