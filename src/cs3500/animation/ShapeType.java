package cs3500.animation;

/**
 * Represents the types of shapes supported by this animation model.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), ELLIPSE("ellipse");

  ShapeType(String str) {
    this.str = str;
  }

  public String str;

  @Override
  public String toString() {
    return this.str;
  }
}
