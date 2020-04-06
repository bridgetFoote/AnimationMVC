import cs3500.animation.controller.EditorViewController;
import cs3500.animation.model.AnimationFrameModel;
import cs3500.animation.model.AnimationFrameOperations;
import cs3500.animation.view.EditorView;
import org.junit.Test;
import org.w3c.dom.events.EventTarget;

import java.awt.*;
import java.awt.event.ActionEvent;

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

  // Test Attempt text description
  @Test(expected = UnsupportedOperationException.class)
  public void testText() {
    v.getTextualDescription();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testXML() {
    v.writeXML("test", 10);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testWriteText() {
    v.writeTextDescription("test");
  }

}
