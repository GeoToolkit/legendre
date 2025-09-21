package com.geotoolkit.recurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolynomialsCalculatorTest {

  double delta = 0.000_000_000_000_000_1;
  @Test
  void f_whenNEquals3AndMEquals2_thenReturn12() {
    int n = 3;
    int m = 2;
    PolynomialsCalculator pCalc = new PolynomialsCalculator();
    Assertions.assertEquals(8, pCalc.f(n, m));
  }


  @Test
  void initiatePnm_whenOrderEqualsTo4_thenArrayOfLength15IsReturned() {
    int order = 4;
    double phi = 0.345_235_436_547_456_9;
    double u = Math.cos(phi);
    PolynomialsCalculator pCalc = new PolynomialsCalculator();
    double[] pnm = pCalc.initiatePnm(order, u);
    Assertions.assertEquals(15, pnm.length);
    Assertions.assertEquals(1.0, pnm[0], delta);
    Assertions.assertEquals(Math.sqrt(3) * u, pnm[2], delta);
  }

  @Test
  void diagonalRecursion_testFNAFL22() {
    double phi = 0.5474567;
    double u = Math.cos(phi);
    double p22 = 3 * u * u;
    double expected_fnp22 = p22 * fnfactor(2, 2);
    PolynomialsCalculator pCalc = new PolynomialsCalculator();

    double[] fnalf = pCalc.initiatePnm(2, u);
    double fnp22 = pCalc.diagonalRecursion(fnalf[2],2, u);

    Assertions.assertEquals(expected_fnp22, fnp22, delta*100);

  }

  @Test
  void columnRecursion_testFNAFL20() {
    double phi = 0.5474567;
    double u = Math.cos(phi);
    double t = Math.sin(phi);
    double p10 = t;
    double expectedfnP10 = p10 * fnfactor(1, 0);
    double p20 = 1.5 * t * t - 0.5;
    double expectedfnP20 = p20 * fnfactor(2, 0);
    PolynomialsCalculator pCalc = new PolynomialsCalculator();

    double[] fnPnm = pCalc.initiatePnm(2, u);
    double fnP10 = pCalc.columnRecursion(fnPnm[0],0, 1, 0, t);
    Assertions.assertEquals(expectedfnP10, fnP10, delta);
    double fnP20 = pCalc.columnRecursion(fnP10, fnPnm[0], 2, 0, t);
    Assertions.assertEquals(expectedfnP20, fnP20, delta);


  }

  private long factorial(int n) {
    if (n == 0) {
      return 1;
    }
    if (n <= 2) {
      return n;
    }
    return n * factorial(n - 1);
  }
  private double fnfactor(int n, int m) {
    double factorialFraction = (double) factorial(n - m) / factorial(n + m);
    int k = m == 0 ? 1 : 2;
    return Math.sqrt(k * (2 * n + 1) * factorialFraction);
  }
}
