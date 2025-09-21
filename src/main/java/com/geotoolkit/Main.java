package com.geotoolkit;

import com.geotoolkit.recurrent.PolynomialsCalculator;
import java.util.concurrent.CountDownLatch;

public class Main {

  public static void main(String[] args) {
    PolynomialsCalculator calculator = new PolynomialsCalculator();
    int order = 1000;
    CountDownLatch latch = new CountDownLatch(order + 1);
    calculator.calculateFNALF(order, 0.654, latch);
    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
}