public class Graph implements GraphInterface {
  /**
   * Initialize the graph with the given number of nodes.
   * 
   * @param numberOfNodes The number of nodes
   */
  public void setNumberOfNodes (int numberOfNodes) {
    this.numberOfNodes = numberOfNodes;
    A = new int[numberOfNodes][numberOfNodes];
  }

  /**
   * Add an undirected edge to the graph.
   * 
   * @param nodeA One node incident at the edge
   * @param nodeB The other node incident at the edge
   */
  public void addEdge (int nodeA, int nodeB) {
    A[nodeA][nodeB] = A[nodeB][nodeA] = 1;
  }

  /**
   * Solve the APSP problem.
   * 
   * @return  A matrix A[i,j] with the next hop on the shortest path from node i
   *          to node j
   */
  public int[][] APSP () {
    int[][] D = APD(A);
    return D;
  }

  /**
   *  Solve the APD problem
   *
   *  @param A  The adjacency matrix of the graph
   *
   *  @return A matrix D[i, j] with the distances between all pairs of nodes
   */
  public int[][] APD (int[][] A) {
    int[][] D = new int[A.length][A.length];
    int[][] Z = matrixMultiply(A, A);

    //printMatrix(Z, "Z");

    // A[i][j] = 1 <=> es existiert Weg der Laenge 1 oder 2 von i nach j
    int[][] A_ = new int[A.length][A.length];
    int j = 0;
    for (int i = 0; i < A_.length; ++i) {
      for (j = 0; j < A_.length; ++j) {
        if (i != j && (A[i][j] == 1 || Z[i][j] > 0)) {
          A_[i][j] = 1;
        }
      }
    }

    boolean terminate = true;
    for (int i = 0; i < A_.length; ++i) {
      for (j = 0; j < A_.length; ++j) {
        if (i != j && A_[i][j] != 1) {
          terminate = false;
          i = j = A_.length; // terminate loops asap
        }
      }
    }

    if (terminate) {
      for (int i = 0; i < D.length; ++i) {
        for (j = 0; j < D.length; ++j) {
          D[i][j] = 2 * A_[i][j] - A[i][j];
        }
      }

      return D;
    }

    int[][] D_ = APD(A_);
    int[][] S = matrixMultiply(A, D_);

    for (int i = 0; i < D.length; ++i) {
      for (j = 0; j < D.length; ++j) {
        D[i][j] = 2 * D_[i][j];
        
        if (S[i][j] < D_[i][j] * Z[i][i]) {
          D[i][j] -= 1;
        }
      }
    }

    return D;
  }

  public int[][] matrixMultiply (int[][] A, int[][] B) {
    int[][] C = new int[A.length][A.length];
    
    int j = 0, k = 0;
    for (int i = 0; i < A.length; ++i) {
      for (j = 0; j < A.length; ++j) {
        //C[i][j] = Integer.MAX_VALUE; // denotes infinity, so to say
        for (k = 0; k < A.length; ++k) {
          //C[i][j] = Math.min(C[i][j], A[i][k] + B[k][j]);
          C[i][j] += A[i][k] * B[k][j];
        }
      }
    }

    //printMatrix(C, "C");

    return C;
  }

  public void printMatrix (int[][] A, String name) {
    System.out.println(name + ":");
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < A.length; ++j) {
        System.out.print(" " + A[i][j]);
      }
      System.out.println();
    }
  }

  private int numberOfNodes = 0;
  private int[][] A = null; // adjancency matrix
}
