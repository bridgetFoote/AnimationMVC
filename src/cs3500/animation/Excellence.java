package cs3500.animation;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.logging.FileHandler;

public final class Excellence {
  public static void main(String[] args) {

    // Validate the argument length and that the arguments come in pairs
    if (args.length < 4 || args.length %2 != 0) {
      System.out.println("Invalid argument length");
      return;
    }

    String inFile = "";
    String outFile = "";
    ViewType vType = null;
    int speed;
    // Assign values to the above variables.
    // TODO: Change back to JOptionPane
    for (int i = 0; i < args.length; i = i + 2) {
      String argType = args[i];
      switch(argType) {
        case "-in":
          inFile = args[i + 1];
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        case "-view":
          if (args[i + 1].equals("text")) {
            vType = ViewType.TEXTVIEW;
          }
          else if (args[i + 1].equals("svg")) {
            vType = ViewType.SVGVIEW;
          }
          else if (args[i + 1].equals("visual")) {
            vType = ViewType.VISUALVIEW;
          }
          else {
            System.out.println("Invalid view type");
            return;
          }
          break;
        case "speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
            if (speed <=0) {
              System.out.println("Speed must be a positive integer");
              return;
            }
          }
          catch (NumberFormatException e) {
            System.out.println("Speed must be a positive integer");
          }
          break;
        default:
          System.out.println("Invalid arguments, please try again");
      }
    }
    // ------------ Launch the program ----------------------
    AnimationModel.Builder b = new AnimationModel.Builder();
    try {
      // Use parseFile to build the model (complete with shapes).
      FileReader inFileReader = new FileReader(inFile);
      AnimationReader reader = new AnimationReader();
      AnimationOperations model = reader.parseFile(inFileReader, b);
      AnimationView view;
      // Check the view type, construct it, and launch the relevant method!
      if (vType.equals(ViewType.TEXTVIEW)) {
        view = new TextView(model);
        view.getTextualDescription();
      }
      else if (vType.equals(ViewType.SVGVIEW)) {
        view = new SVGView(model);
        view.writeXML();
      }
      else if (vType.equals(ViewType.VISUALVIEW)) {
        view = new VisualView(String.format("User's animation for %s", inFile), model);
        view.makeVisible();
      }
      else {
        System.out.println("Invalid view");
      }

    }
    catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }


  }


}
