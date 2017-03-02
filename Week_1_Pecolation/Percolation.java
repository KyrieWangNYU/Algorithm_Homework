package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by kyrie on 2/28/17.
 */
public class Percolation {

    private boolean[] openSites;
    private int gridN;
    private int n;//number of openSites
    private WeightedQuickUnionUF site;
    private WeightedQuickUnionUF siteIsFull;


    public Percolation(int N){
        openSites = new boolean[N * N];
        gridN = N;
        n = 0;
        //Initialize openSites
        for (int i = 0; i < N * N; i++){
            openSites[i] = false;
        }
        //set two virtual sites; site1 is two more than openSites
        site = new WeightedQuickUnionUF(N * N + 2);
        siteIsFull = new WeightedQuickUnionUF(N * N + 1);
    }

    private void indexHelper(int row, int col){
        if (row < 1 || col > gridN || row < 1 || col > gridN){
            throw new java.lang.IndexOutOfBoundsException("Index must be from 1 to N inclusive");
        }
    }

    public void open(int row, int col){
        indexHelper(row, col);
        /**index in array starts from 0 while row and col start
         from 1
         */
        int i = row - 1;
        int j = col - 1;
        int index = i * gridN + j;

        if (openSites[index]){
            return;
        }
        openSites[index] = true;
        n++;

        int siteIndex = index + 1;
        if(i == 0){
            site.union(0,siteIndex);
            siteIsFull.union(0,siteIndex);
        }
        if (i == gridN - 1){
            site.union(siteIndex, gridN * gridN + 1);
        }

        //union the adjacent open sites(left, right, up and down respectively)
        unionIfOpen(siteIndex, i, j - 1);
        unionIfOpen(siteIndex, i, j + 1);
        unionIfOpen(siteIndex, i - 1, j);
        unionIfOpen(siteIndex, i + 1, j);
    }

    private void unionIfOpen(int index, int i, int j){
        if(i >= 0 && i < gridN && j >= 0 && j < gridN){
            if(isOpen(i + 1, j + 1)){
                int newIndex = i * gridN + j + 1;
                site.union(index, newIndex);
                siteIsFull.union(index, newIndex);
            }
        }else{
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public boolean isOpen(int row, int col){
        indexHelper(row, col);
        return (openSites[(row - 1) * gridN + col - 1]);
    }

    public boolean isFull(int row, int col){
        indexHelper(row, col);
        int i = row - 1;
        int j = col - 1;

        int index = i * gridN + j;
        int siteIndex = index + 1;

        boolean bOpen = isOpen(row, col);
        boolean isFull = siteIsFull.connected(0, siteIndex);

        return (bOpen && isFull);
    }

    public int numberOfOpenSites(){
        return n;
    }

    public boolean percolates(){
        if(gridN == 1){
            return (openSites[0]);
        }
        return site.connected(0,gridN * gridN + 1);
    }

    public static void main(String[] args){

    }
}
