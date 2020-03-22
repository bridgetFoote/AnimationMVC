package cs3500.animation;

/**
 * Represents a view of an animation: displays the animation
 * according to the specifications of the type of view.
 */
public interface AnimationView {

  /**
   * Make the view visible to start the game session.
   * N.B. For a {@link AbstractTextualView} this will do nothing.
   */
  void makeVisible();

  /**
   * Get the textual description of the model.
   * N.B. for a {@link AbstractVisualView}, this will do nothing.
   */
  public String getTextualDescription();

  /**
   *
   */
  public String getXMLText();

}
