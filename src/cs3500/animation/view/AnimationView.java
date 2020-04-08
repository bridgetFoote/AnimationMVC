package cs3500.animation.view;

import cs3500.animation.controller.EditorViewController;
import cs3500.animation.model.IShape;

import java.awt.event.ActionListener;

/**
 * Represents a view of an animation: displays the animation
 * according to the specifications of the type of view.
 */
public interface AnimationView {

  /**
   * Make the view visible to start the game session.
   * N.B. For a {@link AbstractTextualView} this will do nothing.
   * @throws UnsupportedOperationException if this is a TextView or a SVGView
   */
  void makeVisible();

  /**
   * Sets the action listeners for all of the buttons in the view, only used in editor view.
   *
   * @param actionEvent is the controller for the buttons.
   * @throws UnsupportedOperationException if it is called in a view other than editor.
   */
  void setButtonListeners(ActionListener actionEvent);

  /**
   * Displays error message thrown by animation panel in a dialog box.
   *
   * @param error is the error thrown by the swing panel.
   * @throws UnsupportedOperationException if not in EditorView
   */
  void showErrorMessage(String error);

  /**
   * Gets the new user inputted frame operation for this model.
   * Only used in the editor view where user can input operations to
   * apply to key frames in this model.
   *
   * @return the string representing the frame operation or the
   *         empty string if the input box is empty.
   */
  String getFrameOperation();

  /**
   * Gets the new user inputted tempo for this model.
   * Only used in the editor view where user can input new speed.
   *
   * @return the string representing the new tempo for the animation
   *         or the empty string if the input box is empty.
   * @throws UnsupportedOperationException for all view types other than EditorView.
   */
  String getNewTempo();

  /**
   * Gets the new user inputted shape for this model.
   * Only used in the editor view where user can input new shape.
   *
   * @return the string representing the new shape that the user wants to input
   *         or the empty string if the input box is empty.
   * @throws UnsupportedOperationException for all view types other than EditorView.
   */
  String getNewShape();

  /**
   * Get the textual description of the model.
   * N.B. for a {@link AbstractVisualView}, this will do nothing.
   * @throws UnsupportedOperationException if this is a VisualView
   */
  String getTextualDescription();

  /**
   * Write the text file from the getTextualDescription method above.
   *
   * @param fileName the name of the file to write to.
   * @throws UnsupportedOperationException is this is a VisualView or a SVGView
   */

  void writeTextDescription(String fileName);

  /**
   * Write the SVG file from the getTextualDescription method above.
   * Uses a private helper to convert the textual description to XML.
   * N.B. for a {@link VisualView} and {@link TextView}, this will do nothing.
   *
   * @param fileName the name of the file to write to
   * @param speed the speed of the animation
   * @throws UnsupportedOperationException if this is a VisualView or TextView
   */
  void writeXML(String fileName, int speed);

  /**
   * Updates the timer in this view by setting the delay to be 1000 / the given speed.
   *
   * @param speed is the integer to divide 1000 by to get the new timer delay.
   * @throws UnsupportedOperationException if this is not an EditorView.
   */
  void updateTimerDelay(int speed);

  /**
   * Sets the value for the last active tick in this animation.
   *
   * @param tick is the integer value for the last active tick.
   * @throws UnsupportedOperationException if it is not in the EditorView.
   */
  void setFinalTick(int tick);

  /**
   * Get the current tick of the animation
   */
  int getCurrentTick();

  /**
   * Set up the controller to handle click events in this view.
   * @param listener the controller
   */
  void addClickListener(EditorViewController listener);


  /**
   * Remove shape from the animation
   * @param clickedShape the shape to remove
   */
  void removeShape(String clickedShape);
}
