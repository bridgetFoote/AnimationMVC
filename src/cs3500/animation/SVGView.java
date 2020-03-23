package cs3500.animation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Represents an animation view that is displayed in SVG format.
 */
public class SVGView extends AbstractTextualView {

  public SVGView(String windowTitle, AnimationOperations readOnlyModel, String outputFile, int tempo) {
    super(windowTitle, readOnlyModel, outputFile, tempo);
    this.sortedShapes = this.sortActions(this.model.getShapeActions());
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
      writer.write("<svg width='");
      writer.append(Integer.toString(readOnlyModel.getCanvasWidth()));
      writer.append("' height='");
      writer.append(Integer.toString(readOnlyModel.getCanvasHeight()));
      writer.append("' version='1.1' xmlns='http://www.w3.org/2000/svg'>\n");

      for (IShape s: readOnlyModel.getShapes()) {
        this.shapeToSVG(s, writer);
      }
      writer.append("</svg>");
      writer.close();
    } catch (IOException ioe) {
    }
  }

  HashMap<IShape, List<ShapeAction>> sortedShapes;

  private void shapeToSVG(IShape shape, BufferedWriter writer) {
    if (shape.getActions().size() == 0) {
      return;
    }
    try {
      if (shape.getShapeType().equals("rect")) {
        writer.append("<" + shape.getShapeType() + " id='" + shape.getName()
                + "' x='" + Integer.toString(this.sortedShapes.get(shape).get(0).startPoint.get(0))
                + "' y='" + Integer.toString(this.sortedShapes.get(shape).get(0).startPoint.get(1))
                + "' width='" + Integer.toString(this.sortedShapes.get(shape).get(0).startWidth)
                + "' height='" + Integer.toString(this.sortedShapes.get(shape).get(0).startHeight)
                + "' fill='rgb("
                + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("red"))
                + "," + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("green"))
                + "," + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("blue"))
                + ")' visibility='visible' >\n");
      } else {
        writer.append("<" + shape.getShapeType() + " id='" + shape.getName()
                + "' cx='" + Integer.toString(this.sortedShapes.get(shape).get(0).startPoint.get(0))
                + "' cy='" + Integer.toString(this.sortedShapes.get(shape).get(0).startPoint.get(1))
                + "' rx='" + Integer.toString(this.sortedShapes.get(shape).get(0).startWidth / 2)
                + "' ry='" + Integer.toString(this.sortedShapes.get(shape).get(0).startHeight / 2)
                + "' fill='rgb("
                + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("red"))
                + "," + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("green"))
                + "," + Integer.toString(this.sortedShapes.get(shape).get(0).startColor.getColorGradient("blue"))
                + ")' visibility='visible' >\n");
      }
      for (ShapeAction action: this.sortedShapes.get(shape)) {
        String prefix = "<animate attributeType='xml' begin='" + Integer.toString(action.getStartTick())
                + "' dur='" + Integer.toString(action.getEndTick() - action.getStartTick());
        this.decomposeAction(shape, action, writer, prefix,
                this.sortedShapes.get(shape).indexOf(action) == (this.sortedShapes.get(shape).size() - 1));
      }
      writer.append("</" + shape.getShapeType() + ">\n");
    } catch (IOException ioe) {
    }
  }

  private HashMap<IShape, List<ShapeAction>> sortActions(HashMap<IShape, List<ShapeAction>> map) {
    for (IShape s: map.keySet()) {
      final class Comp implements Comparator<ShapeAction> {

        @Override
        public int compare(ShapeAction o1, ShapeAction o2) {
          if (o1.startTick < o2.startTick) {
            return -1;
          } else if (o2.startTick > o2.startTick) {
            return 1;
          } else if (o1.endTick > o2.endTick) {
            return 1;
          } else if (o1.endTick < o2.endTick) {
            return -1;
          } else {
            return 0;
          }
        }
      }
      List<ShapeAction> sorted = s.getActions();
      sorted.sort(new Comp());
      map.replace(s, sorted);
    }
    return map;
  }

  private void decomposeAction(IShape shape, ShapeAction action, BufferedWriter writer, String prefix, boolean lastAction) {
    try {
      String fill;
      if (lastAction) {
        fill = "remove";
      } else {
        fill = "freeze";
      }
      if (action instanceof Move) {
        if (action.changeInCoord("x")) {
          if (shape.getShapeType().equals("rect")) {
            writer.append(prefix  + " attributeName='x' from='"
                    + action.getCoord("x", "start") + "' to='"
                    + action.getCoord("x", "end") + "' fill='" + fill + "' />\n");
          } else {
            writer.append(prefix  + " attributeName='cx' from='"
                    + action.getCoord("x", "start") + "' to='"
                    + action.getCoord("x", "end") + "' fill='" + fill + "' />\n");
          }
        }
        if (action.changeInCoord("y")) {
          if (shape.getShapeType().equals("rect")) {
            writer.append(prefix  + " attributeName='y' from='"
                    + action.getCoord("y", "start") + "' to='"
                    + action.getCoord("y", "end") + "' fill='" + fill + "' />\n");
          } else {
            writer.append(prefix  + " attributeName='cy' from='"
                    + action.getCoord("y", "start") + "' to='"
                    + action.getCoord("y", "end") + "' fill='" + fill + "' />\n");
          }
        }
      }
      if (action.colorChange()) {
        writer.append(prefix  + " attributeName='fill' from='rgb("
                + Integer.toString(action.startColor.getColorGradient("red")) + ","
                + Integer.toString(action.startColor.getColorGradient("green")) + ","
                + Integer.toString(action.startColor.getColorGradient("blue")) + ")' to='rgb("
                + Integer.toString(action.endColor.getColorGradient("red")) + ","
                + Integer.toString(action.endColor.getColorGradient("green")) + ","
                + Integer.toString(action.endColor.getColorGradient("blue")) + ")' fill='"
                + fill + "' />\n");
      }
      if (action.sizeChange("width")) {
        if (shape.getShapeType().equals("rect")) {
          writer.append(prefix + " attributeName='width' from='"
                  + action.startWidth + "' to='"
                  + action.endWidth + "' fill='" + fill + "' />\n");
        } else {
          writer.append(prefix + " attributeName='rx' from='"
                  + (action.startWidth / 2) + "' to='"
                  + (action.endWidth / 2) + "' fill='" + fill + "' />\n");
        }
      }
      if (action.sizeChange("height")) {
        if (shape.getShapeType().equals("rect")) {
          writer.append(prefix + " attributeName='height' from='"
                  + action.startHeight + "' to='"
                  + action.endHeight + "' fill='" + fill + "' />\n");
        } else {
          writer.append(prefix + " attributeName='ry' from='"
                  + (action.startHeight / 2) + "' to='"
                  + (action.endHeight / 2) + "' fill='" + fill + "' />\n");
        }
      }
    } catch (IOException ioe) {
    }
  }
}
