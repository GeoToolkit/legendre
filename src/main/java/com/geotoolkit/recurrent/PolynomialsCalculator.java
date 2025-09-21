package com.geotoolkit.recurrent;

public class PolynomialsCalculator {

  /**
   * Initiates array of appropriate length with two first elements.
   * @param degreeOrder maximum degree/order of desired associated Legendre functions
   * @param radiansPhi latitude in radians.
   * @return initial linear array with two first elements.
   */
  double[] initiatePnm(int degreeOrder, double radiansPhi) {
    degreeOrder++;
    double p00 = 1.0d;
    double p10 = Math.sqrt(3) * Math.cos(radiansPhi);
    double[] pnm = new double[(degreeOrder * degreeOrder + degreeOrder) / 2];
    pnm[f(0,0)] = p00;
    pnm[f(1,0)] = p10;
    return pnm;
  }

  void diagonalRecursion(double Pnm, int Nmax) {

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
