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
  @Override
  public String getXMLText() {
    String description = this.getTextualDescription();
    String output = "<svg width=\"700\" height=\"500\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n";
    // FLOW: Split the description string by lines, find the shape/motion tags and build the XML string
    String[] lines = description.split("\n");
    for (int i = 0; i < lines.length; i++) {
      String[] tmp = lines[i].split(" ");
      if (tmp[0].equals("shape")) {
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
      else if (tmp[0].equals("motion")) {
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
        output = output.concat(String.format("</%s>\n", findShape(lines[i - 1].split(" ")[2])));
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

  /**
   * Use the getXMLText method to write the XML string to a file for viewing in a browser.
   */
  public void writeXML() {
    String xml = this.getXMLText();
    try {
      FileWriter writer = new FileWriter("Shapes.xml", true);
      writer.write(xml);
      writer.close();
    }
    catch (IOException e) {
      // TODO
    }
  }

}
