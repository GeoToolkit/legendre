package com.geotoolkit.recurrent;

import com.geotoolkit.recurrent.parallel.ColumnWorker;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PolynomialsCalculator {

  public double[] calculateFNALF(int degree, double phi) {
    double u = Math.cos(phi);
    double t = Math.sin(phi);

    double[] pnm = initiatePnm(degree, u);

    CountDownLatch latch = new CountDownLatch(degree + 1);
    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    executorService.submit(new ColumnWorker(pnm,degree, 0, t, latch));
    for (int i = 2; i <= degree; i++) {
      pnm[f(i, i)] = diagonalSingleRecursion(pnm[f(i - 1, i - 1)], i, u, pnm);
      System.out.println("P[" + i + ", " + i + "] = " + pnm[f(i, i)]);
      Runnable columnWorker = new ColumnWorker(pnm,degree, i - 1, t, latch);
      executorService.submit(columnWorker);
    }
    latch.countDown();
    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return pnm;
  }

  /**
   * Initiates array of appropriate length with two first elements.
   *
   * @param degreeOrder maximum degree/order of desired fully normalized associated Legendre
   *                    functions
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

  double diagonalSingleRecursion(double pm_1m_1, int m, double u, double[] pnm) {
    pnm[f(m, m)] = u * Math.sqrt((double) (2 * m + 1) / (2 * m)) * pm_1m_1;
    return pnm[f(m, m)];
  }

  public void columnRecursion(double[] pnm, int degree, int m, double t) {
    double pn_1m = columnSingleRecursion(pnm[f(m, m)], 0, m + 1, m, t, pnm);
    int n = m + 1;
    System.out.println("P[" + n + ", " + m + "] = " + pn_1m);
    if (m < degree - 1) {
      for (int i = m + 2; i <= degree; i++) {
        double currentPnm = columnSingleRecursion(pnm[f(i-1, m)], pnm[f(i-2, m)], i, m, t, pnm);
        System.out.println("P[" + i + ", " + m + "] = " + currentPnm);
      }
    }
  }

  double columnSingleRecursion(double pn_1m, double pn_2m, int n, int m, double t, double[] pnm) {
    double anm = Math.sqrt(((double) (2 * n - 1) * (2 * n + 1)) / ((n - m) * (n + m)));
    double bnm = Math.sqrt(
        ((double) (2 * n + 1) * (n + m - 1) * (n - m - 1)) / ((n - m) * (n + m) * (2 * n - 3)));
    pnm[f(n, m)] = anm * t * pn_1m - bnm * pn_2m;
    return pnm[f(n, m)];
  }

  /**
   * Transforms 2D triangular array into 1D linear array. Starts from zero index.
   *
   * @return index of analogous linear array element.
   */
  public int f(int n, int m) {
    return (n * n + n) / 2 + m;
  }


}
