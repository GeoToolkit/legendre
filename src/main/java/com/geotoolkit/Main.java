package com.geotoolkit;

import com.geotoolkit.recurrent.PolynomialsCalculator;


public class Main {

  public static void main(String[] args) {
    int degree = 1000;
    PolynomialsCalculator calculator = new PolynomialsCalculator();
    double[] pnm = calculator.calculateFNALF(degree, 0.654);

    boolean check = true;
    for(int i = 0; i <= degree; i++) {
      for(int j = 0; j <= i; j++) {
        check = pnm[calculator.f(i, j)] != 0;
      }
    }
    System.out.println(check);
  }
}