import java.util.Scanner;

public class MergeLists {
  public static void main (String[] args) {
    List<Integer> l = new List<Integer>();
    List<Integer> m = new List<Integer>();
    Scanner scanner = new Scanner(System.in);

    for (String s: scanner.nextLine().split(" ")) {
      l.insert(Integer.parseInt(s));
    }

    for (String s: scanner.nextLine().split(" ")) {
      m.insert(Integer.parseInt(s));
    }

    /*
    for (int i = 0; i < 12; ++i) {
      l.delete(i);
      m.delete(i);
    }
    System.out.println(l.toString());
    System.out.println(m.toString());
    */

    List<Integer> n = merge(l, m);
    System.out.println(n.toString());
  }

  /**
   *
   */
  public static <T extends Comparable<T>> List<T> merge(List<T> l, List<T> m) {
    List<T> result = new List<T>();
    List<T>.Node<T> lcurrent = l.getHead();
    List<T>.Node<T> mcurrent = m.getHead();
    List<T>.Node<T> tmp = null;

    // insert in this specific case takes O(1) since the tail is checked upfront
    // in the insert method and we simply append it as new tail
    while (lcurrent != null || mcurrent != null) {
      if (lcurrent == null || mcurrent == null) {
        tmp = (lcurrent == null ? mcurrent : lcurrent);
        result.insert(tmp.data);
        tmp = tmp.next;
        
        if (lcurrent == null) {
          mcurrent = tmp;
        } else {
          lcurrent = tmp;
        }

        continue;
      }

      if (lcurrent.compareTo(mcurrent) >= 0) {
        result.insert(mcurrent.data);
        mcurrent = mcurrent.next;
      } else {
        result.insert(lcurrent.data);
        lcurrent = lcurrent.next;
      }
    }

    return result;
  }
}
