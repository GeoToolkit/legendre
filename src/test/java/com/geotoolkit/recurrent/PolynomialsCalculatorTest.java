package com.geotoolkit.recurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolynomialsCalculatorTest {

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
    Assertions.assertEquals(1.0, pnm[0],0.000_000_000_000_000_1);
    Assertions.assertEquals(Math.sqrt(3) * u, pnm[1],0.000_000_000_000_000_1);
  }
}
