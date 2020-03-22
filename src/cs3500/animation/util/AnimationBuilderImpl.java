package cs3500.animation.util;

import cs3500.animation.AnimationOperations;
import cs3500.animation.IShape;
import cs3500.animation.ShapeAction;

import java.util.HashMap;
import java.util.List;

public final class AnimationBuilderImpl implements AnimationOperations {

  @Override
  public List<IShape> getShapes() {
    return null;
  }

  @Override
  public HashMap<IShape, List<ShapeAction>> getShapeActions() {
    return null;
  }

  @Override
  public List<IShape> getShapesAtTick(int tick) {
    return null;
  }

  @Override
  public void addShape(int redGradient, int greenGradient, int blueGradient, int width, int height, String name, String shapeType) {

  }

  @Override
  public void addShapeAction(String shapeName, int startTick, int endTick, int startPointX, int startPointY, int endPointX, int endPointY, int startRedGradient, int startGreenGradient, int startBlueGradient, int endRedGradient, int endGreenGradient, int endBlueGradient, int endWidth, int endHeight) {

  }

  public static final class Builder implements AnimationBuilder<AnimationOperations> {
    @Override
    public AnimationOperations build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationOperations> setBounds(int x, int y, int width, int height) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationOperations> declareShape(String name, String type) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationOperations> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
      return null;
    }
  }
}
