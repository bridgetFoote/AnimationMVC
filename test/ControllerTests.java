import cs3500.animation.controller.EditorViewController;
import cs3500.animation.model.AnimationFrameModel;
import cs3500.animation.model.AnimationFrameOperations;
import cs3500.animation.view.EditorView;
import org.junit.Test;

/**
 * Class to test the edit controller.
 */

public class ControllerTests {
  AnimationFrameOperations m = new AnimationFrameModel();
  EditorView v = new EditorView("Animation", m, 1);
  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    EditorViewController c = new EditorViewController(m, null);
  }

  // Test get user input from text box
  @Test
  public void testUpdateTime() {

  }

}
