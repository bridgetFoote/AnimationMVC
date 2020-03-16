import cs3500.animation.AnimationModel;
import cs3500.animation.SimpleShape;
import cs3500.animation.Rectangle;
import cs3500.animation.Circle;
import cs3500.animation.RGBColor;
import cs3500.animation.ShapeAction;
import cs3500.animation.Move;
import cs3500.animation.Stay;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Public class to test AnimationModel functionality.
 */
public class AnimationModelTest {

  AnimationModel m = new AnimationModel();

  @Test (expected = IllegalArgumentException.class)
  public void testNoOverlappingActions() {
    SimpleShape r = new Rectangle(new RGBColor(0, 0, 255),
            3.1, 2.4, "blueRectangle");
    ShapeAction a = new Move(1, 6, Arrays.asList(0, 0),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction b = new Stay(2, 4, Arrays.asList(0, 0),
            Arrays.asList(0, 0), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction c = new Stay(6, 11, Arrays.asList(5, 5),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    m.addShape(r);
    List<SimpleShape> sList = new ArrayList<SimpleShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<SimpleShape, List<ShapeAction>> map = new HashMap<SimpleShape, List<ShapeAction>>();
    map.put(r, Arrays.asList(a));
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(r, b);
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(r, c);
    map.get(r).add(c);
    assertEquals(map, m.getShapeActions());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNoTeleportation() {
    SimpleShape r = new Rectangle(new RGBColor(0, 0, 255),
            3.1, 2.4, "blueRectangle");
    ShapeAction a = new Move(1, 6, Arrays.asList(0, 0),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction b = new Stay(6, 11, Arrays.asList(0, 0),
            Arrays.asList(0, 0), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction c = new Stay(6, 11, Arrays.asList(5, 5),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    m.addShape(r);
    List<SimpleShape> sList = new ArrayList<SimpleShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<SimpleShape, List<ShapeAction>> map = new HashMap<SimpleShape, List<ShapeAction>>();
    map.put(r, Arrays.asList(a));
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(r, b);
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(r, c);
    map.get(r).add(c);
    assertEquals(map, m.getShapeActions());
  }

  @Test (expected = IllegalArgumentException.class)
  public void noOverlappingShapes() {
    SimpleShape r = new Rectangle(new RGBColor(0, 0, 255),
            3.1, 2.4, "blueRectangle");
    SimpleShape c = new Circle(new RGBColor(255, 0, 0),
            2.0, 2.0, "redCircle");
    ShapeAction a = new Move(1, 6, Arrays.asList(0, 0),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction b = new Stay(6, 11, Arrays.asList(5, 5),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction d = new Move(0, 8, Arrays.asList(1, 1),
            Arrays.asList(5, 5), new RGBColor(255, 0, 0),
            new RGBColor(255, 0, 0), 2.0, 2.0);
    ShapeAction e = new Move(0, 8, Arrays.asList(1, 1),
            Arrays.asList(5, 4), new RGBColor(255, 0, 0),
            new RGBColor(255, 0, 0), 2.0, 2.0);
    m.addShape(r);
    List<SimpleShape> sList = new ArrayList<SimpleShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShape(c);
    sList.remove(r);
    sList.add(c);
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<SimpleShape, List<ShapeAction>> map = new HashMap<SimpleShape, List<ShapeAction>>();
    map.put(r, Arrays.asList(a));
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(r, b);
    map.get(r).add(b);
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(c, d);
    assertEquals(map, m.getShapeActions());
    m.addShapeAction(c, e);
    map.get(c).add(e);
    assertEquals(map, m.getShapeActions());
  }

  @Test
  public void testTextDescription() {
    String output = "";
    output = output.concat("shape redCircle circle\n");
    output = output.concat("motion redCircle 0 1 1 2.0 2.0 255 0 0  8 5 4 2.0 2.0 255 0 0\n");
    output = output.concat("\n");
    output = output.concat("shape blueRectangle rectangle\n");
    output = output.concat("motion blueRectangle 1 0 0 3.1 2.4 0 0 255  6 5 5 3.1 2.4 0 0 255\n");
    output = output.concat("motion blueRectangle 6 5 5 3.1 2.4 0 0 255  11 5 5 3.1 2.4 0 0 255\n");
    output = output.concat("\n");
    String out1 = "shape redCircle circle\n"
            + "motion redCircle 0 1 1 2.0 2.0 255 0 0  8 5 4 2.0 2.0 255 0 0\n"
            + "\n"
            + "shape blueRectangle rectangle\n"
            + "motion bluRectangle 1 0 0 3.1 2.4 0 0 255  6 5 5 3.1 2.4 0 0 255\n"
            + "motion bluRectangle 6 5 5 3.1 2.4 0 0 255  11 5 5 3.1 2.4 0 0 255\n\n";
    SimpleShape r = new Rectangle(new RGBColor(0, 0, 255),
            3.1, 2.4, "blueRectangle");
    SimpleShape c = new Circle(new RGBColor(255, 0, 0),
            2.0, 2.0, "redCircle");
    ShapeAction a = new Move(1, 6, Arrays.asList(0, 0),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction b = new Stay(6, 11, Arrays.asList(5, 5),
            Arrays.asList(5, 5), new RGBColor(0, 0, 255),
            new RGBColor(0, 0, 255), 3.1, 2.4);
    ShapeAction e = new Move(0, 8, Arrays.asList(1, 1),
            Arrays.asList(5, 4), new RGBColor(255, 0, 0),
            new RGBColor(255, 0, 0), 2.0, 2.0);
    m.addShape(r);
    m.addShape(c);
    m.addShapeAction(r, a);
    m.addShapeAction(r, b);
    assertEquals(2, m.getShapeActions().get(r).size());
    m.addShapeAction(c, e);
    assertEquals(output, m.getTextDescription());
  }
}
