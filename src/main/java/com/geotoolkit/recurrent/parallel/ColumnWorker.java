package com.geotoolkit.recurrent.parallel;

import com.geotoolkit.recurrent.PolynomialsCalculator;
import java.util.concurrent.CountDownLatch;

public class ColumnWorker implements Runnable {
  private final double[] pnm;
  private final int degree;
  private final int m;
  private final double t;
  private final CountDownLatch latch;

  public ColumnWorker(double[] pnm, int degree, int m, double t, CountDownLatch latch) {
    this.pnm = pnm;
    this.degree = degree;
    this.m = m;
    this.t = t;
    this.latch = latch;
  }

  @Override
  public void run() {
    PolynomialsCalculator pCalc = new PolynomialsCalculator();
    pCalc.columnRecursion(pnm, degree, m, t);
    latch.countDown();
  }
}
