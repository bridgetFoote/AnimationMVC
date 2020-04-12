package cs3500.animation.model;

public class Posn {

  private int x;
  private int y;

  public Posn(int x, int y) {
    if ((x < 0) || (y < 0)) {
      throw new IllegalArgumentException("Coordinates must be greater than or equal to 0");
    }
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
}
