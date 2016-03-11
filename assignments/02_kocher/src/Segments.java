/**
 *  Implementation of a the segment intersection algorithm ReportCuts.
 *
 *  Assignment 03, Advanced Algorithms & Data Structures, Summer term 2016.
 *  Department of Computer Sciences, University of Salzburg.
 *
 *  @author Daniel Kocher, 0926293
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class Segments {
  /**
   *  Main function.
   *  Processes the command-line arguments and calls the ReportCuts method.
   *
   *  @param args A List of 4-tuples each of which consists of 4 Double values
   */
  public static void main (String[] args) {
    reportCuts(readSegments());
  }

  /**
   *
   */
  public static List<Point> readSegments () {
    Scanner scanner = new Scanner(System.in);
    List<Point> segments = new List<Point>();
    Point[] associates = new Point[2];
    double[] values = new double[4];
    int idx = 0;
    while (scanner.hasNext()) {
      // parse values in line
      idx = 0;
      for (String s: scanner.nextLine().split("\\s")) {
        try {
          values[idx++] = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
          nfe.printStackTrace();
        }
      }

      // x coordinates are equal => vertical segment
      if (values[0] == values[2]) { 
        segments.insert(new VSegment(values[0], values[1], values[2], values[3]));
        continue; 
      }

      // y coordiantes are equal => horizontal segment
      associates[0] = new Point(values[0], values[1], null);
      associates[1] = new Point(values[2], values[3], associates[0]);
      associates[0].setAssociate(associates[1]);
      segments.insert(associates[0]);
      segments.insert(associates[1]);
    }
    
    return segments;
  }

  /**
   *  
   */
  public static void reportCuts (List<Point> s) {
    System.out.println("s.size() = " + s.size() + " and contains:");
    System.out.println(s.toString());

    if (s.size() <= 1) {
      System.out.println("end");
      return;
    }

    System.out.println("divide");

    List<Point> s1 = s;
    List<Point> s2 = new List<Point>();
    s2.setHead(s1.getHeadHalf());
    List.disconnect(s2, s);

    reportCuts(s1);
    reportCuts(s2);
  }
}
