package com.geotoolkit.recurrent;

public class PolynomialsCalculator {

  /**
   * Initiates array of appropriate length with two first elements.
   *
   * @param degreeOrder maximum degree/order of desired fully normalized associated Legendre functions
   * @param u           cos of latitude.
   * @return initial linear array with two first elements.
   */
  double[] initiatePnm(int degreeOrder, double u) {
    degreeOrder++;
    double p00 = 1.0d;
    double p11 = Math.sqrt(3) * u;
    double[] pnm = new double[(degreeOrder * degreeOrder + degreeOrder) / 2];
    pnm[f(0, 0)] = p00;
    pnm[f(1, 1)] = p11;
    return pnm;
  }

  double diagonalRecursion(double pm_1m_1, int m, double u) {
    return u * Math.sqrt((double)(2 * m + 1)/(2 * m)) * pm_1m_1;
  }

  double columnRecursion(double pn_1m, double pn_2m, int n, int m, double t) {
    double anm = Math.sqrt(((double)(2 * n - 1) * (2 * n + 1)) / ((n - m) * (n + m)));
    double bnm = Math.sqrt(((double)(2 * n + 1) * (n + m - 1) * (n - m - 1)) / ((n - m) * (n + m) * (2 * n - 3)));
  return anm * t * pn_1m - bnm * pn_2m;
  }

  /**
   * Transforms 2D triangular array into 1D linear array. Starts from zero index.
   *
   * @return index of analogous linear array element.
   */
  int f(int n, int m) {
    return (n * n + n) / 2 + m;
  }
}
