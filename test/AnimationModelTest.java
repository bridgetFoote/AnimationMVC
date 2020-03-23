import cs3500.animation.*;
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

  /*@Test (expected = IllegalArgumentException.class)
  public void testNoOverlappingActions() {
    AbstractShape r = new Rectangle(new RGBColor(0, 0, 255),
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
    List<AbstractShape> sList = new ArrayList<AbstractShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<AbstractShape, List<ShapeAction>> map = new HashMap<AbstractShape, List<ShapeAction>>();
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
    AbstractShape r = new Rectangle(new RGBColor(0, 0, 255),
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
    List<AbstractShape> sList = new ArrayList<AbstractShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<AbstractShape, List<ShapeAction>> map = new HashMap<AbstractShape, List<ShapeAction>>();
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
    AbstractShape r = new Rectangle(new RGBColor(0, 0, 255),
            3.1, 2.4, "blueRectangle");
    AbstractShape c = new Circle(new RGBColor(255, 0, 0),
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
    List<AbstractShape> sList = new ArrayList<AbstractShape>();
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShape(c);
    sList.remove(r);
    sList.add(c);
    sList.add(r);
    assertEquals(sList, m.getShapes());
    m.addShapeAction(r, a);
    HashMap<AbstractShape, List<ShapeAction>> map = new HashMap<AbstractShape, List<ShapeAction>>();
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
  }*/

  @Test
  public void testTextDescription() {
    m.addShape("R1", ShapeType.RECTANGLE.toString());
    m.addShape("E1", ShapeType.ELLIPSE.toString());
    m.addShapeAction("R1", 1, 5, 100, 10, 400, 10,
            255, 0, 0, 0,0,255,
            100, 200, 100, 200);
    m.addShapeAction("E1", 1, 5, 400, 10, 100, 10,
            255, 0, 0, 255,0,0,
            100, 200, 100, 200);
    AnimationView v = new TextView(m);
    System.out.println(v.getTextualDescription());
    AnimationView svg = new SVGView(m);
    System.out.println(svg.getXMLText());
  }
}
