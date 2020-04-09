package cs3500.animation.provider.view;

import java.util.ArrayList;


/**
 * Represents the main operations for the ExCELlence shape builder program. Users can create shapes
 * and give them motions, and also remove shapes and motions.
 */
public interface ExCELlenceOperations extends ExCELlenceOperationsReadOnly {

  /**
   * Creates a shape of the given parameters.
   *
   * @param name  the name of the shape
   * @param shape the type of shape (currently limited to "Rectangle" and "Ellipse")
   * @param p     the position of the shape
   * @param w     the width of the shape
   * @param h     the height of the shape
   * @param c     the color of the shape
   */
  void create(String name, String shape, Posn p, double w, double h, IColor c);

  /**
   * adds given motion of the given shape and parameters to shape's list of motions.
   *
   * @param name the shape to be moved
   * @param m    the motion to be added
   * @throws IllegalArgumentException if shape does not exist
   */
  void addMotion(String name, IMotion m);

  /**
   * removes given motion of the given shape and parameters to shape's list of motions.
   *
   * @param name the shape from which the we are removing the motion.
   * @param m    the motion to be removed
   * @throws IllegalArgumentException if shape does not exist, or shape doesn't have motion
   */
  void removeMotion(String name, IMotion m);


  /**
   * Returns an ArrayList of all IMotions for given shape.
   *
   * @return an ArrayList<Motion></Motion> holding specified Motions.
   * @throws IllegalArgumentException if shape does not exist.
   */
  ArrayList<IMotion> getMotions(String name);

  /**
   * adds given keyframe of the given shape and parameters to shape's list of keyframes.
   *
   * @param name the shape to be moved
   * @param k    the keyframe to be added
   * @throws IllegalArgumentException if shape does not exist
   */
  void addKeyframe(String name, Keyframe k);

  /**
   * removes given motion of the given shape and parameters to shape's list of motions.
   *
   * @param name the shape from which the we are removing the keyframe.
   * @param k    the keyframe to be removed
   * @throws IllegalArgumentException if shape does not exist, or shape doesn't have motion
   */
  void removeKeyframe(String name, Keyframe k);


  /**
   * Returns an ArrayList of all keyframes for given shape.
   *
   * @return an ArrayList<Keyframe></Keyframe> holding specified keyframes.
   * @throws IllegalArgumentException if shape does not exist.
   */
  ArrayList<Keyframe> getKeyframes(String name);

  /**
   * Obtains a list of all shapes in the model at given tick, interpolating their motions.
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
   */
  IShape getShape(String name);

  /**
   * Returns key(name) of the given IShape.
   *
   * @param s the shape for which we want to return the key.
   * @return the desired shape
   * @throws IllegalArgumentException if the shape does not exist in the model
   */
  String getKey(IShape s);

  /**
   * Removes shape of given name from model.
   *
   * @param name the name of the shape we want to remove
   * @throws IllegalArgumentException if the shape does not exist in the model
   */
  void removeShape(String name);

  /**
   * Sorts given shape's list of motions by chronological order.
   *
   * @param name the shape whose list of motions we want to sort
   * @throws IllegalArgumentException if shape does not exist
   */
  void sortMotions(String name);

  /**
   * Sorts given shape's list of keyframes by chronological order.
   *
   * @param name the shape whose list of keyframes we want to sort
   * @throws IllegalArgumentException if shape does not exist
   */
  void sortKeyframes(String name);

  /**
   * Validates the motions of given shape to ensure there is no overlap between motions and
   * therefore no teleportation. The position of a shape at the end of a motion should be that same
   * at the beginning of next motion.
   *
   * @param name is a String that represents the key for an IShape in the list of shapes.
   */
  void validateMotions(String name);

  /**
   * Sets the top-left corner of the window.
   * @param p the top-left position in the model
   */
  void setTopLeft(Posn p);

  /**
   * sets the width of the window.
   * @param w the width
   */
  void setWindowWidth(int w);

  /**
   * sets the height of the window.
   * @param h the height
   */
  void setWindowHeight(int h);

}
