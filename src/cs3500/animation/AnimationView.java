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
   * Write the SVG file from the getTextualDescription method above.
   * Uses a private helper to convert the textual description to XML.
   * N.B. for a {@link VisualView} and {@link TextView}, this will do nothing.
   */
  public void writeXML();

}
