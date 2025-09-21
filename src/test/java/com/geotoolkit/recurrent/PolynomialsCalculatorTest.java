package com.geotoolkit.recurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolynomialsCalculatorTest {

  @Test
  void f_whenNEquals3AndMEquals2_thenReturn12() {
    int n = 3;
    int m = 2;
    PolynomialsCalculator pCalc = new PolynomialsCalculator();
    Assertions.assertEquals(12, pCalc.f(n, m));
  }
}
