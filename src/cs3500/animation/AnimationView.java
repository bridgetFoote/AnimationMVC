package cs3500.animation;

/**
 * Represents a view of an animation: displays the animation
 * according to the specifications of the type of view.
 */
public interface AnimationView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
