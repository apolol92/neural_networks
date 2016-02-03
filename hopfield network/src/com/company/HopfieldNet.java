package com.company;

import matrix.Matrix;

import java.util.ArrayList;

/**
 * Created by apolol92 on 02.02.2016.
 * This neural network is a hopfield  network.
 */
public class HopfieldNet {
    /**
     * This is the trained layer
     */
    public Matrix V;

    /**
     * Traines the neural network by using a binary string list
     * @param binaryStrList a list of binary strings.. "01001001110" etc.
     */
    public HopfieldNet(ArrayList<String> binaryStrList) {
        this.V = new Matrix(binaryStrList.get(0).length(),binaryStrList.get(0).length());

        for(int r = 0; r < this.V.ROWS; r++) {
            for(int c = 0; c < this.V.COLS; c++) {
                if(r!=c) {
                    this.V.values[r][c] = sumSpecial(binaryStrList, r, c);
                }
                else {
                    this.V.values[r][c] = 0;
                }
            }
        }
    }

    /**
     * Use this formula for creating a hopfield network
     * @param sList
     * @param i
     * @param j
     * @return
     */
    private double sumSpecial(ArrayList<String> sList, int i, int j) {
        double sum = 0;
        for(int k = 0; k < sList.size(); k++) {
            sum+=(2.0*(double) Character.digit(sList.get(k).charAt(i), 10)-1.0) * (2.0*(double) Character.digit(sList.get(k).charAt(j), 10)-1.0);
        }
        return sum;
    }

    /**
     * Classify a new binary input as matrix
     * @param m, a correctly dimensioned matrix
     * @return the classiffied matrix
     */
    public Matrix classify(Matrix m) {
        return m.multiply(this.V);
    }

}