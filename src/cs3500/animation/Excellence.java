package cs3500.animation;

import javax.swing.*;
import java.util.Objects;

public final class Excellence {
  public static void main(String[] args) {
    if ((args.length < 4) || ((args.length % 2) != 0)) {
      JOptionPane.showMessageDialog(new JDialog(), "Invalid input length.");
      return;
    }

    String inputFile = null;
    ViewType typeOfView = null;
    String outputFile = null;
    Integer ticksPerSecond = null;

    for (int i = 0; i < args.length; i = i + 2) {
      switch (args[i]) {
        case "-in":
          inputFile = args[i + 1];
        case "-view":
          if (args[i + 1].equals("text")) {
            typeOfView = ViewType.TEXTVIEW;
          } else if (args[i + 1].equals("visual")) {
            typeOfView = ViewType.VISUALVIEW;
          } else if (args[i + 1].equals("svg")) {
            typeOfView = ViewType.SVGVIEW;
          } else {
            JOptionPane.showMessageDialog(new JDialog(), "Given view type is not valid.");
            return;
          }
        case "-out":
          outputFile = args[i + 1];
        case "-speed":
          try {
            ticksPerSecond = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(new JDialog(), "Given speed is not valid");
            return;
          }
        default:
          JOptionPane.showMessageDialog(new JDialog(), "Given argument tag is not valid.");
          return;
        }
      }

    if (Objects.isNull(inputFile) || Objects.isNull(typeOfView)) {
      JOptionPane.showMessageDialog(new JDialog(), "Input file or view type arguments not specified.");
      return;
    }
  }
}
