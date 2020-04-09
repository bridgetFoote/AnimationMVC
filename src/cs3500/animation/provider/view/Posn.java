package cs3500.animation.provider.view;

/**
 * Represents a coordinate pair, has an x component and a y component.
 */
public class Posn {

  /**
   * Creates a new Posn object with the given x and y coordinates.
   *
   * @param x is the x coordinate.
   * @param y is the y coordinate
   * @throws IllegalArgumentException if either of the inputs are negative.
   */
  public Posn(int x, int y) {
    if ((x < 0) || (y < 0)) {
      throw new IllegalArgumentException("Coordinates cannot be negative");
    }
    this.x = x;
    this.y = y;
  }

  private int x;
  private int y;

  /**
   * Returns the x coordinate in this Posn.
   *
   * @return the x coordinate.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate inf this Posn.
   *
   * @return the y coordinate.
   */
  public int getY() {
    return this.y;
  }
}
