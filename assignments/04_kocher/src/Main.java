public class Main {
  public static void main (String[] args) {
    Graph g = new Graph();
    int numberOfNodes = 4;

    g.setNumberOfNodes(numberOfNodes);

    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 0);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 0);
    g.addEdge(3, 1);
    g.addEdge(3, 2);

    int[][] apsp = g.APSP();
    g.printMatrix(apsp, "APSP");
  }
}
