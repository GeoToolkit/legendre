package com.geotoolkit.recurrent;

public class PolynomialsCalculator {

  /**
   * Initiates array of appropriate length with two first elements.
   * @param degreeOrder maximum degree/order of desired associated Legendre functions
   * @param u cos of latitude.
   * @return initial linear array with two first elements.
   */
  double[] initiatePnm(int degreeOrder, double u) {
    degreeOrder++;
    double p00 = 1.0d;
    double p10 = Math.sqrt(3) * u;
    double[] pnm = new double[(degreeOrder * degreeOrder + degreeOrder) / 2];
    pnm[f(0,0)] = p00;
    pnm[f(1,0)] = p10;
    return pnm;
  }

  void diagonalRecursion(double Pm_1m_1, int m, double u) {

  }
  /**
   * Transforms 2D triangular array into 1D linear array. Starts from zero index.
   *
   * @return index of analogous linear array element.
   */
  int f(int n, int m) {
    return (n * n +  n) / 2 + m;
  }
}
