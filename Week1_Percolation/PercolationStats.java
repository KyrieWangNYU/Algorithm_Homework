package com.company;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by kyrie on 3/1/17.
 */
public class PercolationStats {

    private double[] thresholds;
    private int experiment;
    private int size;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials){
        if (n <= 0){
            throw new java.lang.IllegalArgumentException("n is out of bounds");
        }
        if (trials <= 0){
            throw new java.lang.IllegalArgumentException("trials is out of bounds");
        }

        size = n;
        experiment = trials;
        thresholds = new double[experiment];

        for (int i = 0; i < trials; i++){
            thresholds[i] = findPercolationThreshold();
        }

        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        double confidenceFraction = (1.96 * stddev()) / Math.sqrt(experiment);
        confidenceLo = mean - confidenceFraction;
        confidenceHi = mean + confidenceFraction;
    }

    private double findPercolationThreshold(){
        Percolation site = new Percolation(size);
        int row, col;
        int count = 0;

        while(!site.percolates()){
            do{
                row = StdRandom.uniform(size) + 1;
                col = StdRandom.uniform(size) + 1;
            }while(site.isOpen(row, col));

            count++;
            site.open(row, col);
        }

        return count/(Math.pow(size,2));
    }

    public double mean(){
        return mean;
    }

    public double stddev(){
        return stddev;
    }


    public double confidenceLo(){
        return confidenceLo;
    }

    public double confidenceHi(){
        return confidenceHi;
    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean = %f" + stats.mean());
        System.out.println("stddev = %f" + stats.stddev());
        System.out.println("95%% confidence interval = %f, %f" + stats.confidenceLo() + stats.confidenceHi());

    }
}
