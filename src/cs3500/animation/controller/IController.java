package cs3500.animation.controller;

/**
 * The controller interface for the animation program.
 * The functions here have been designed to give
 * control to the controller, and the primary
 * operation for the controller to function
 */

public interface IController {

  /**
   * Start the program, i.e. give control to the controller.
   */
  void go();

  /**
   * Use a click from the user to determine if a shape was clicked.
   * If a shape was clicked, remove the shape from the animation.
   * Do nothing if no shape was clicked.
   * @param xPixel the x location of the click
   * @param yPixel the y location of the click
   */
  void handleMouseClick(int xPixel, int yPixel);
}
