import cs3500.animation.model.*;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Tests for the MVC design of the Animator.
 */
public class ModelTests {
  AnimationFrameOperations m = new AnimationFrameModel();

  /**
   * AnimationFrameModel Tests.
   */

  // Test bad keyframe: invalid tick
  @Test(expected = IllegalArgumentException.class)
  public void testBadFrameTime() {
    KeyFrame f1 = new KeyFrame(-1,100, 100, 50,
            50, 100, 100, 100);
  }

  // Bad keyframe: invalid width/height
  @Test(expected = IllegalArgumentException.class)
  public void testBadFrameSize() {
    KeyFrame f1 = new KeyFrame(-1,100, 100, 50,
            0, 100, 100, 100);
    }

    // Model parameters
  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    ShapeWithKeyFrames s = new ShapeWithKeyFrames("s", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    ShapeWithKeyFrames s = new ShapeWithKeyFrames("", ShapeType.RECTANGLE);
  }

  // Test addKeyFrame
  @Test
  public void testAddKeyFrame() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    assertTrue(m.getShapeWithKeyFrames("r").hasFrameAt(0));

  }

  // Test addKeyFrame with shape that isn't there
  @Test(expected = IllegalArgumentException.class)
  public void testBadAdd() {
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
  }

  // Test removeKeyFrame
  @Test
  public void testRemoveKeyFrame() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    m.addKeyFrame("r", 1, 101, 100, 100, 100,
            255, 0, 0);
    m.removeKeyFrame("r", 1);
    assertFalse(m.getShapeWithKeyFrames("r").hasFrameAt(1));
  }

  // Test remove key frame where a frame DNE
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyFrameDNE() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    m.addKeyFrame("r", 1, 101, 100, 100, 100,
            255, 0, 0);
    m.removeKeyFrame("r", 2);
    assertFalse(m.getShapeWithKeyFrames("r").hasFrameAt(1));
  }

  // Test that two key frames are the same
  @Test
  public void testSameKeyFrame() {
    KeyFrame f1 = new KeyFrame(0,1,1,1,1,
            1,1,1);
    KeyFrame f2 = new KeyFrame(0,1,1,1,1,
            1,1,1);
    assertEquals(f1,f2);
  }

  // Test that two colors are the same
  @Test
  public void testSameColor() {
    RGBColor one = new RGBColor(255,0,0);
    RGBColor two = new RGBColor(255,0,0);
    assertEquals(one, two);
  }

  // Test remove shape
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShape() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.removeShape("r");
    m.getShapeWithKeyFrames("r");
  }

  // Test remove shape that DNE
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeDNE() {
    m.removeShape("r");
  }

  // Test edit key frame
  @Test
  public void testEditKeyFrame() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    m.addKeyFrame("r", 1, 101, 100, 100, 100,
            255, 0, 0);
    m.editKeyFrame("r", 1, 200, 100, 100, 100,
            255, 0, 0);
    KeyFrame newFrame = m.getShapeWithKeyFrames("r").getTickAt(1);
    KeyFrame changedFrame = new KeyFrame(1, 200, 100, 100, 100,
            255, 0, 0);
    assertEquals(newFrame, changedFrame);
  }

  // Test edit key frame where shape DNE
  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyFrameShapeDNE() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    m.addKeyFrame("r", 1, 101, 100, 100, 100,
            255, 0, 0);
    m.editKeyFrame("a", 10, 200, 100, 100, 100,
            255, 0, 0);
  }

  // Test edit key frame where tick DNE
  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyFrameDNE() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r",ShapeType.RECTANGLE.toString());
    m.addKeyFrame("r", 0, 100, 100, 100, 100,
            255, 0, 0);
    m.addKeyFrame("r", 1, 101, 100, 100, 100,
            255, 0, 0);
    m.editKeyFrame("r", 10, 200, 100, 100, 100,
            255, 0, 0);
  }

  // Test get shape
  @Test
  public void testGetShape() {
    ShapeWithKeyFrames r = new ShapeWithKeyFrames("r", ShapeType.RECTANGLE);
    m.addShape("r", ShapeType.RECTANGLE.toString());
    assertEquals(r, m.getShapeWithKeyFrames("r"));
  }

}
