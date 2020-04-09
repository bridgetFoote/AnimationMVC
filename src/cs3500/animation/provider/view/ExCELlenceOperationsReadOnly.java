package cs3500.animation.provider.view;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * To represent the read-only methods for the ExCELlence shape builder program.
 */
public interface ExCELlenceOperationsReadOnly {
  /**
   * Returns an ArrayList of all IMotions for given shape.
   *
   * @return an ArrayList<Motion></Motion> holding specified Motions.
   * @throws IllegalArgumentException if shape does not exist.
   */
  ArrayList<IMotion> getMotions(String name);

  /**
   * Obtains a list of all shapes in the model at given tick.
   *
   * @param tick the given time
   * @return list of shapes in the model at time tick
   * @throws IllegalStateException if there are no shapes
   */
  ArrayList<IShape> getShapesAt(int tick);

  /**
   * Obtains a list of all shapes in the model at given tick, interpolating their keyframes.
   *
   * @param tick the given time
   * @return list of shapes in the model at time tick
   * @throws IllegalStateException if there are no shapes
   */
  ArrayList<IShape> getShapesAtKeyframe(int tick);

  /**
   * Returns shape of the given name.
   *
   * @param name the name of the shape we want to get
   * @return the desired shape
   * @throws IllegalArgumentException if the shape does not exist in the model
   *
   */
  IShape getShape(String name);

  /**
   * Gets a copy of the current shapes within the model as a HashMap including their given names.
   */
  HashMap<String, IShape> getShapes();

  /**
   * Represents the current state of the model as a string.
   *
   * @return a text representing the current State of the ExCELlenceOperations.
   */
  String getState();

  /**
   * Gets the top-left corner Posn of the model, determined by the canvas.
   * shapes with x or y positions below this value will not be visible or only partially seen.
   * @return the top-left corner
   */
  Posn getTopLeft();

  /**
   * Gets the width of the window of the model, determined by the canvas.
   * @return the width
   */
  int getWindowWidth();

  /**
   * Gets the height of the window of the model, determined by the canvas.
   * @return the height
   */
  int getWindowHeight();
}
