public class Binom {
  private static int[][] coeff = null;

  public static void main (String[] args) {
    int threshold = 20;
    int i = 0;
    for (int n = 0; n < threshold; ++n) {
      for (i = 0; i < threshold; ++i) {
        System.out.println("n = " + n + ", i = " + i + ": binom = " + binom(n, i));
      }
    }
  }

  public static int binom (final int n, final int i) {
    coeff = new int[n + 1][i + 1];

    int l = 0, k = 0;
    for (k = 0; k <= n; ++k) {
      for (l = 0; l <= i; ++l) {
        coeff[k][l] = Integer.MAX_VALUE;
      }
    }

    for (k = 0; k <= n; ++k) {
      coeff[k][0] = 1;
      for (l = 1; l <= i; ++l) {
        coeff[0][l] = 0;
      }
    }

    return lookupBinom(n, i);
  }

  public static int lookupBinom (final int n, final int i) {
    if (coeff[n][i] == Integer.MAX_VALUE) {
      coeff[n][i] = lookupBinom(n - 1, i) + lookupBinom(n - 1, i - 1);
    }

    return coeff[n][i];
  }
}