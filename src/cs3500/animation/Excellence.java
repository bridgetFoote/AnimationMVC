package cs3500.animation;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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

    List<String> l = new ArrayList<String>();
    for (int i = 0; i < args.length; i++) {
      l.add(args[i]);
    }

    int i = 0;
    while (i < l.size()) {
      switch (l.get(i)) {
        case "-in":
          inputFile = l.get(i + 1);
          i = i + 2;
        case "-view":
          if (l.get(i + 1).equals("text")) {
            typeOfView = ViewType.TEXTVIEW;
            i = i + 2;
          } else if (l.get(i + 1).equals("visual")) {
            typeOfView = ViewType.VISUALVIEW;
            i = i + 2;
          } else if (l.get(i + 1).equals("svg")) {
            typeOfView = ViewType.SVGVIEW;
            i = i + 2;
          } else {
            JOptionPane.showMessageDialog(new JDialog(), "Given view type is not valid.");
          }
        case "-out":
          outputFile = l.get(i + 1);
          i = i + 2;
        case "-speed":
          try {
            ticksPerSecond = Integer.parseInt(l.get(i + 1));
            i = i + 2;
          } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(new JDialog(), "Given speed is not valid");
            return;
          }
      }
    }

    if (Objects.isNull(inputFile) || Objects.isNull(typeOfView)) {
      JOptionPane.showMessageDialog(new JDialog(), "Input file or view type arguments not specified.");
      return;
    }
    if (Objects.isNull(ticksPerSecond)) {
      ticksPerSecond = 1;
    }

    File file = new File(inputFile);
    AnimationReader ar = new AnimationReader();
    try {
      AnimationOperations model = ar.parseFile(new FileReader(file), new AnimationModel.Builder());
      AnimationView view;
      if (typeOfView.compareTo(ViewType.TEXTVIEW) == 0) {
        view = new TextView("Animation", model, outputFile, ticksPerSecond);
      } else if (typeOfView.compareTo(ViewType.SVGVIEW) == 0) {
        view = new SVGView("Animation", model, outputFile, ticksPerSecond);
      } else {
        view = new VisualView("Animation", model, ticksPerSecond);
      }
      view.makeVisible();
    } catch (FileNotFoundException fnfe) {
      JOptionPane.showMessageDialog(new JDialog(), "File not found.");
    }
  }
}
