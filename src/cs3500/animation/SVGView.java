package cs3500.animation;
import java.io.FileWriter;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.List;

/**
 * Represents an animation view that is displayed in SVG format. This class extends the abstract
 * class {@link AbstractVisualView} and uses the getTextualDescription method to generate the
 * XML file used for animation.
 */
public class SVGView extends AbstractTextualView {

  public SVGView(AnimationOperations readOnlyModel) {
    super(readOnlyModel);
  }

  /**
   * Writes the model state to an XML "string" which can be used to
   * view the animation in a browser.
   * @return A string representation of the XML that can be written to a file.
   */

  private String getXMLText() {
    String description = this.getTextualDescription();
    String[] canvasString = description.split("\n")[0].split(" ");
    String output = String.format("<svg width=\"%s\" height=\"%s\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n", canvasString[3], canvasString[4]);
    // FLOW: Split the description string by lines, find the shape/motion tags and build the XML string
    String[] lines = description.split("\n");
    String currentShapeType = "";
    for (int i = 0; i < lines.length; i++) {
      String[] tmp = lines[i].split(" ");
      // If the line declares a rectangle
      if (tmp[0].equals("shape") && tmp[2].equals("rectangle")) {
        // Create the new shape tag
        output = output.concat(String.format("<%s id=\"%s\"",
                findShape(tmp[2]), tmp[1]));
        // Add the initial position from the next line. Add try-catch if no animation exists.
        try {
          String[] tmp2 = lines[i + 1].split(" ");
          output = output.concat(String.format(" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" " +
                  "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >",tmp2[3],tmp2[3], tmp2[4], tmp2[5],
                  tmp2[6], tmp2[7], tmp2[8]));
        }
        catch (IndexOutOfBoundsException e) {
          System.out.println("Index out of bounds");
        }
      }
      // If the line declares an ellipse
      else if(tmp[0].equals("shape") && tmp[2].equals("ellipse")) {
        // Create the new shape tag
        output = output.concat(String.format("<%s id=\"%s\"",
                findShape(tmp[2]), tmp[1]));
        // Add the initial position from the next line. Add try-catch if no animation exists.
        try {
          String[] tmp2 = lines[i + 1].split(" ");
          output = output.concat(String.format(" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\" " +
                          "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >",tmp2[3],tmp2[3], tmp2[4], tmp2[5],
                  tmp2[6], tmp2[7], tmp2[8]));
        }
        catch (IndexOutOfBoundsException e) {
          // Do nothing
        }
      }
      // if the motion is for a rectangle
      else if (tmp[0].equals("motion") && findShape(lines[i - 1].split(" ")[2]).equals("rect") ) {
        // Add an animation tag. Have one animation tag per attribute (might need to change).
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                "dur=\"%d000ms\" attributeName=\"x\" from=\"%s\" to=\"%s\" fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[3], tmp[12]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"y\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n", tmp[2],
                Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[4], tmp[13]));

        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"width\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[5], tmp[14]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"height\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[6], tmp[15]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"fill\" from=\"rgb(%s,%s,%s)\" " +
                        "to=\"rgb(%s,%s,%s)\" fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[7], tmp[8],
                tmp[9], tmp[16], tmp[17], tmp[18]));
        // Once all animations have been added, close the shape's tag.
        output = output.concat(String.format("</%s>\n", "rect"));
      }
      // if the motion is for an ellipse
      else if (tmp[0].equals("motion") && findShape(lines[i - 1].split(" ")[2]).equals("ellipse") ) {
        // Add an animation tag. Have one animation tag per attribute (might need to change).
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"cx\" from=\"%s\" to=\"%s\" fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[3], tmp[12]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"cy\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n", tmp[2],
                Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[4], tmp[13]));

        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"rx\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[5], tmp[14]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"ry\" from=\"%s\" to=\"%s\" " +
                        "fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[6], tmp[15]));
        output = output.concat(String.format("<animate attributeType=\"xml\" begin=\"%s000ms\" " +
                        "dur=\"%d000ms\" attributeName=\"fill\" from=\"rgb(%s,%s,%s)\" " +
                        "to=\"rgb(%s,%s,%s)\" fill=\"freeze\" /> \n",
                tmp[2], Integer.parseInt(tmp[11]) - Integer.parseInt(tmp[2]),tmp[7], tmp[8],
                tmp[9], tmp[16], tmp[17], tmp[18]));
        // Once all animations have been added, close the shape's tag.
        output = output.concat(String.format("</%s>\n", "ellipse"));
      }
      else {
        // Do nothing for now.
      }
    }
    return output.concat("</svg>");
  }

  /**
   * Helper method for getXMLText() that converts a string to an XML shape tag
   * @param s the string from the text description.
   * @return an XML string that can be read as a shape tag.
   * @throws IllegalArgumentException if s isn't a rectangle or ellipse (the only allowed shapes).
   */
  private String findShape(String s) {
    if (s.equals("rectangle")) {
      return "rect";
    }
    else if (s.equals("ellipse")) {
      return "ellipse";
    }
    else {
      return "";
      //throw new IllegalArgumentException("Invalid shape in text description");
    }
  }

  @Override
  public void writeXML() {
    String xml = this.getXMLText();
    try {
      FileWriter writer = new FileWriter("Shapes.xml", true);
      writer.write(xml);
      writer.close();
    }
    catch (IOException e) {
      // Do nothing.
    }
  }

}
