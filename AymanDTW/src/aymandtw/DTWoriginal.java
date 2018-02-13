/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymandtw;

import java.util.Vector;

/**
 *
 * @author ayman.ezzat
 */
public class DTWoriginal {
        
        protected Vector<AccPoint>  seq1;
	protected Vector<AccPoint> seq2;
	protected int[][] warpingPath;
	
	protected int n;
	protected int m;
	protected int K;
	
	protected double warpingDistance;

    public DTWoriginal(Vector<AccPoint> sample, Vector<AccPoint>  templete) {
		seq1 = sample;
		seq2 = templete;
		
		n = seq1.size();	
		m = seq2.size();
		K = 1;
		
		warpingPath = new int[n + m][2];	// max(n, m) <= K < n + m
		warpingDistance = 0.0;
		
		this.compute();
	}
	
	public void compute() {
		double accumulatedDistance = 0.0;
		
		double[][] d = new double[n][m];	// local distances
		double[][] D = new double[n][m];	// global distances
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = distanceBetween(seq1.get(i), seq2.get(j));
			}
		}
		
		D[0][0] = d[0][0];
		
		for (int i = 1; i < n; i++) {
			D[i][0] = d[i][0] + D[i - 1][0];
		}

		for (int j = 1; j < m; j++) {
			D[0][j] = d[0][j] + D[0][j - 1];
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				accumulatedDistance = Math.min(Math.min(D[i-1][j], D[i-1][j-1]), D[i][j-1]);
				accumulatedDistance += d[i][j];
				D[i][j] = accumulatedDistance;
			}
		}
		accumulatedDistance = D[n - 1][m - 1];

		int i = n - 1;
		int j = m - 1;
		int minIndex = 1;
	
		warpingPath[K - 1][0] = i;
		warpingPath[K - 1][1] = j;
		
		while ((i + j) != 0) {
			if (i == 0) {
				j -= 1;
			} else if (j == 0) {
				i -= 1;
			} else {	// i != 0 && j != 0
				double[] array = { D[i - 1][j], D[i][j - 1], D[i - 1][j - 1] };
				minIndex = this.getIndexOfMinimum(array);

				if (minIndex == 0) {
					i -= 1;
				} else if (minIndex == 1) {
					j -= 1;
				} else if (minIndex == 2) {
					i -= 1;
					j -= 1;
				}
			} // end else
			K++;
			warpingPath[K - 1][0] = i;
			warpingPath[K - 1][1] = j;
		} // end while
		warpingDistance = accumulatedDistance / K;
		
		this.reversePath(warpingPath);
	}
	
	/**
	 * Changes the order of the warping path (increasing order)
	 * 
	 * @param path	the warping path in reverse order
	 */
	protected void reversePath(int[][] path) {
		int[][] newPath = new int[K][2];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 2; j++) {
				newPath[i][j] = path[K - i - 1][j];
			}
		}
		warpingPath = newPath;
	}
	/**
	 * Returns the warping distance
	 * 
	 * @return
	 */
	public double getDistance() {
		return warpingDistance;
	}
	
	/**
	 * Computes a distance between two points
	 * 
	 * @param p1	the point 1
	 * @param p2	the point 2
	 * @return		the distance between two points
	 */
	protected double distanceBetween(AccPoint p1, AccPoint p2) {
		//return (p1 - p2) * (p1 - p2);
                return Math.sqrt(Math.pow(p1.ACCX-p2.ACCX,2)+Math.pow(p1.ACCY-p2.ACCY,2)+Math.pow(p1.ACCZ-p2.ACCZ,2));
               // return Math.sqrt(Math.pow(p1.ACCX-p2.ACCX,2)+Math.pow(p1.ACCY-p2.ACCY,2));
	}
    protected int getIndexOfMinimum(double[] array) {
		int index = 0;
		double val = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] < val) {
				val = array[i];
				index = i;
			}
		}
		return index;
	}
}
