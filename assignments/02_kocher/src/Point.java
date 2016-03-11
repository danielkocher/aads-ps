/**
 *
 */

public class Point implements Comparable<Point> {
  public final double x1;
  public final double y1;
  public Point associate;

  /**
   *  Default constructor.
   */
  public Point (double x1, double y1, Point associate) {
    this.x1 = x1;
    this.y1 = y1;
    this.associate = associate;
  }

  /**
   *  Getter y coordinate.
   */
  public double x () {
    return x1;
  }

  /**
   *  Getter y coordinate.
   */
  public double y () {
    return y1;
  }

  /**
   *  Setter associate.
   */
  public void setAssociate (Point associate) {
    this.associate = associate;
  }

  @Override
  public int compareTo (Point other) {
    return ((int)(x1 - other.x1));
  }

  @Override
  public String toString () {
    return ("(" + x1 + ", " + y1 + ")");
  } 
}
