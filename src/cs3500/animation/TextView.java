package cs3500.animation;

/**
 * Represents an animation view displayed textually,
 * with each shape listed with their movements underneath organized by tick.
 */
public class TextView extends AbstractTextualView {

  /**
   * Creates a text display animation view that displays this animation
   * in textual form.
   *
   * @param readOnlyModel is the read only version of the model associated with
   *                      this animation.
   */
  public TextView(AnimationOperations readOnlyModel) {
    super(readOnlyModel);
  }
}
