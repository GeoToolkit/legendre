package com.geotoolkit.recurrent;

public class PolynomialsCalculator {

  double[] Pnm;

  /**
   * Transforms 2D triangular array into 1D linear array.
   * Starts from zero index.
   * @return index of analogous linear array element.
   */
  int f(int n, int m) {
    return (n * n + 3 * n) / 2 + 1 + m;
  }
}
