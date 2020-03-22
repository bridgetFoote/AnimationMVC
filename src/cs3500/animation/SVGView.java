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
    } catch (IOException ioe) {
    }
  }

  HashMap<IShape, List<ShapeAction>> sortedShapes;

  private void shapeToSVG(IShape shape, BufferedWriter writer) {
    if (shape.getActions().size() == 0) {
      return;
    }
    try {
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
      for (ShapeAction action: this.sortedShapes.get(shape)) {
        // convert the actions
      }
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
}
