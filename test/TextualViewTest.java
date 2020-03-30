import cs3500.animation.model.AnimationOperations;
import cs3500.animation.model.AnimationModel;
import cs3500.animation.view.AnimationView;
import cs3500.animation.view.SVGView;
import cs3500.animation.view.TextView;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Contains tests for the textual views of our animation to ensure that the output
 * is formatted correctly.
 */
public class TextualViewTest {

  AnimationOperations model = new AnimationModel();

  /**
   * Tests that the svg view outputs the svg description of the animation
   * to the specified file in the correct format.
   */
  @Test
  public void testSVGFormatting() {
    model.addShape("Rectangle", "rectangle");
    model.addShape("Ellipse", "ellipse");
    model.addShapeAction("Rectangle", 0,
            5, 4, 4, 9, 4,
            0, 255, 0,
            123, 100, 0, 4,
            4, 4, 4);
    model.addShapeAction("Rectangle", 5,
            10, 9, 4, 9, 9,
            0, 255, 0,
            255, 0, 0, 4,
            4, 4, 4);
    model.addShapeAction("Ellipse", 0, 10,
            10, 10, 10, 10,
            0, 0, 255,
            0, 0, 255, 6,
            6, 36, 36);
    AnimationView view = new SVGView(model);
    view.writeXML("test1", 4);
    String content = "";
    try {
      content = new String(Files.readAllBytes(Paths.get("resources/test1")));
    }
    catch (IOException e) {
      // Do nothing.
    }
    assertEquals("<svg viewBox=\"0 0 0 0\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"Rectangle\" x=\"4\" y=\"4\" width=\"4\" height=\"4\" " +
            "fill=\"rgb(0,255,0)\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" " +
            "begin=\"0.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"x\" from=\"4\" to=\"9\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"y\" from=\"4\" to=\"4\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"width\" from=\"4\" to=\"4\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"height\" from=\"4\" to=\"4\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(123,100,0)\" " +
            "fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"1250.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"x\" from=\"9\" to=\"9\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"1250.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"y\" from=\"4\" to=\"9\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"1250.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"width\" from=\"4\" to=\"4\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"1250.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"height\" from=\"4\" to=\"4\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"1250.00ms\" dur=\"1250.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(255,0,0)\" " +
            "fill=\"freeze\" /> \n"
            + "</rect>\n"
            + "<ellipse id=\"Ellipse\" cx=\"10\" cy=\"10\" rx=\"3\" ry=\"3\" " +
            "fill=\"rgb(0,0,255)\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"0.00ms\" " +
            "dur=\"2500.00ms\" "
            + "attributeName=\"cx\" from=\"10\" to=\"10\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"2500.00ms\" "
            + "attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"2500.00ms\" "
            + "attributeName=\"rx\" from=\"3\" to=\"18\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"2500.00ms\" "
            + "attributeName=\"ry\" from=\"3\" to=\"18\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"2500.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,0,255)\" " +
            "fill=\"freeze\" /> \n"
            + "</ellipse>\n"
            + "</svg>", content);
  }


  /**
   * Tests the TextView.
   */
  @Test
  public void testTextDescriptionView() {
    model.addShape("Rectangle", "rectangle");
    model.addShape("Ellipse", "ellipse");
    model.addShapeAction("Rectangle", 0,
            5, 4, 4, 9, 4,
            0, 255, 0,
            123, 100, 0, 4,
            4, 4, 4);
    model.addShapeAction("Rectangle", 5,
            10, 9, 4, 9, 9,
            0, 255, 0,
            255, 0, 0, 4,
            4, 4, 4);
    model.addShapeAction("Ellipse", 0, 10,
            10, 10, 10, 10,
            0, 0, 255,
            0, 0, 255, 6,
            6, 36, 36);
    AnimationView view = new TextView(model);
    view.writeTextDescription("test2");
    String content = "";
    try {
      content = new String(Files.readAllBytes(Paths.get("resources/test2")));
    }
    catch (IOException e) {
      // Do nothing
    }
    assertEquals("canvas 0 0 0 0\n"
            + "shape Rectangle rectangle\n"
            + "motion Rectangle 0 4 4 4 4 0 255 0  5 9 4 4 4 123 100 0\n"
            + "motion Rectangle 5 9 4 4 4 0 255 0  10 9 9 4 4 255 0 0\n"
            + "shape Ellipse ellipse\n"
            + "motion Ellipse 0 10 10 6 6 0 0 255  10 10 10 36 36 0 0 255\n", content);
  }

  /**
   * Tests that new views can't be created when the model is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void modelCantBeNull() {
    AnimationView svgView = new SVGView(null);
    assertNull(svgView);
    AnimationView textView = new TextView(null);
    assertNull(textView);
  }

}
