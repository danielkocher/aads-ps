import java.util.Map;
import java.util.HashMap;
import java.util.Random;

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

    //printMatrix(D, "D");

    Map<Integer, int[][]> Ds = new HashMap<Integer, int[][]>() {{
      put(0, new int[D.length][D.length]);
      put(1, new int[D.length][D.length]);
      put(2, new int[D.length][D.length]);
    }};
    Map<Integer, int[][]> Ws = new HashMap<Integer, int[][]>() {{
      put(0, new int[D.length][D.length]);
      put(1, new int[D.length][D.length]);
      put(2, new int[D.length][D.length]);
    }};

    int j = 0, k = 0;
    for (int s = 0; s <= 2; ++s) {
      for (k = 0; k < D.length; ++k) {
        for (j = 0; j < D.length; ++j) {
          if ((D[k][j] + 1) % 3 == s) {
            Ds.get(s)[k][j] = 1;
          }
        }
      }

      //printMatrix(Ds.get(s), "Ds(" + s + ")");
      Ws.put(s, BPWM(A, Ds.get(s)));
    }

    /*
    for (Map.Entry<Integer, int[][]> ws: Ws.entrySet()) {
      printMatrix(ws.getValue(), "Ws(" + ws.getKey() + ")");
    }
    */

    int[][] S = new int[D.length][D.length];

    for (int i = 0; i < S.length; ++i) {
      for (j = 0; j < S.length; ++j) {
        S[i][j] = Ws.get(D[i][j] % 3)[i][j];
      }
    }

    return S;
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
    int[][] Z = matrixMultiply(A, A, 1);

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
    int[][] S = matrixMultiply(A, D_, 1);

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

  public int[][] matrixMultiply (int[][] A, int[][] B, int factor) {
    int[][] C = new int[A.length][A.length];
    
    int j = 0, k = 0;
    for (int i = 0; i < A.length; ++i) {
      for (j = 0; j < A.length; ++j) {
        //C[i][j] = Integer.MAX_VALUE; // denotes infinity, so to say
        for (k = 0; k < A.length; ++k) {
          //C[i][j] = Math.min(C[i][j], A[i][k] + B[k][j]);
          C[i][j] += A[i][k] * B[k][j];
        }

        C[i][j] *= factor;
      }
    }

    //printMatrix(C, "C");

    return C;
  }

  public int[][] BPWM (int[][] A, int[][] B) {
    //printMatrix(A, "A");
    //printMatrix(B, "B");
    
    int[][] W = matrixMultiply(A, B, -1);

    //printMatrix(W, "W");

    int i = 0, j = 0, r = 0, u = 0, v = 0, k = 0, toKeep = 0;
    int[][] Z = null;
    Random random = new Random();
    double bound = Math.log10(A.length);
    for (int t = 0; t <= Math.floor(bound); ++t) {
      r = ((int)Math.pow(2, t));
      for (u = 0; u < Math.ceil(4 * bound); ++u) {
        int[][] AR = new int[A.length][A.length];
        int[][] BR = new int[A.length][A.length];
        int[] Rk = new int[A.length];

        for (i = 0; i < r; ++i) {
          Rk[random.nextInt(A.length)] = 1;
        }

        for (i = 0; i < A.length; ++i) {
          for (k = 0; k < A.length; ++k) {
            AR[i][k] = k * Rk[k] * A[i][k];
          }
        }

        for (k = 0; k < A.length; ++k) {
          for (j = 0; j < A.length; ++j) {
            BR[k][j] = Rk[k] * B[k][j];
          }
        }

        //printMatrix(AR, "AR");
        //printMatrix(BR, "BR");

        Z = matrixMultiply(AR, BR, 1);

        //printMatrix(Z, "Z = AR * BR");

        for (i = 0; i < W.length; ++i) {
          for (j = 0; j < W.length; ++j) {
            if (W[i][j] < 0) {
              for (k = 0; k < A.length; ++k) {
                if (AR[i][k] == 1 && BR[k][j] == 1) {
                  W[i][j] = Z[i][j];
                  break;
                }
              }
            }
          }
        }
      }
    }

    for (i = 0; i < W.length; ++i) {
      for (j = 0; j < W.length; ++j) {
        if (W[i][j] < 0) {
          for (k = 0; k < W.length; ++k) {
            if (A[i][k] == 1 && B[k][j] == 1) {
              W[i][j] = k;
              break;
            }
          }
        }
      }
    }

    return W;
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

  public int[][] trivialBPWM (int[][] A, int[][] B) {
    int[][] W = new int[A.length][A.length];

    int i = 0, j = 0, k = 0;
    for (i = 0; i < W.length; ++i) {
      for (j = 0; j < W.length; ++j) {
        for (k = 0; k < W.length; ++k) {
          if (A[i][k] == 1 && B[k][j] == 1) {
            W[i][j] = k;
            break;
          }
        }
      }
    }

    return W;
  }

  private int numberOfNodes = 0;
  private int[][] A = null; // adjacency matrix
}
