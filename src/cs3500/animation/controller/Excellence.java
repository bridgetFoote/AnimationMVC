package cs3500.animation.controller;

import cs3500.animation.model.*;
import cs3500.animation.provider.view.ExCELlenceModel;
import cs3500.animation.provider.view.ExCELlenceOperations;
import cs3500.animation.provider.view.ExCELlenceOperationsReadOnly;
import cs3500.animation.view.*;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main class for running the animation.
 */
public final class Excellence {
  /**
   * Main method for running the animation.
   * @param args the arguments to run the animation.
   */
  public static void main(String[] args) {

    // Validate the argument length and that the arguments come in pairs
    if (args.length < 4 || args.length % 2 != 0) {
      JOptionPane.showMessageDialog(new JDialog(), "Invalid argument length");
      return;
    }

    String inFile = "";
    String outFile = "";
    ViewType vType = null;
    int speed = 1;
    // Assign values to the above variables.
    for (int i = 0; i < args.length; i = i + 2) {
      String argType = args[i];
      switch (argType) {
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
          else if (args[i + 1].equals("edit")) {
            vType = ViewType.EDITORVIEW;
          }
          else if (args[i + 1].equals("provider")) {
            vType = ViewType.PROVIDERVIEW;
          }
          else {
            JOptionPane.showMessageDialog(new JDialog(), "Invalid view type");
            return;
          }
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
            if (speed <= 0) {
              JOptionPane.showMessageDialog(new JDialog(), "Speed must be a positive integer");
              return;
            }
          }
          catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(new JDialog(), "Speed must be a positive integer");
          }
          break;
        default:
          JOptionPane.showMessageDialog(new JDialog(), "Invalid arguments, please try again");
      }
    }
    // ------------ Launch the program ----------------------
    AnimationFrameModel.Builder b = new AnimationFrameModel.Builder();
    ExCELlenceModel.Builder b2 = new ExCELlenceModel.Builder();
    try {
      // Use parseFile to build the model (complete with shapes).
      FileReader inFileReader = new FileReader(inFile);
      AnimationReader reader = new AnimationReader();

      AnimationFrameOperations model = reader.parseFile(inFileReader, b);
      ExCELlenceOperations model2 = reader.parseFile(inFileReader, b2);

      AnimationView view;
      // Check the view type, construct it, and launch the relevant method!
      if (vType.equals(ViewType.TEXTVIEW)) {
        view = new TextView(model);
        if (outFile.equals("")) {
          System.out.println(view.getTextualDescription());
        } else {
          view.writeTextDescription(outFile);
        }
      }
      else if (vType.equals(ViewType.SVGVIEW)) {
        view = new SVGView(model);
        if (speed <= 0) {
          throw new IllegalArgumentException("Speed must be a positive integer");
        }
        view.writeXML(outFile, speed);
      }
      else if (vType.equals(ViewType.VISUALVIEW)) {
        view = new VisualView(String.format("User's animation for %s", inFile), model, speed);
        view.makeVisible();
      }
      else if (vType.equals(ViewType.EDITORVIEW)) {
        view = new EditorView(String.format("User's animation for %s", inFile), model, speed);
        IController controller = new EditorViewController(model, view);
        controller.go();
        // view.makeVisible();
      }
      else if (vType.equals(ViewType.PROVIDERVIEW)) {
        cs3500.animation.provider.view.EditorView view2 = new cs3500.animation.provider.view.EditorView(String.format("User's animation for %s", inFile),
                model.translateModel());
        view2.showView();
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
