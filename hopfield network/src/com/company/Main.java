package com.company;

import matrix.Matrix;

import java.util.ArrayList;

public class Main {
    public static String oImg3 =
            ".........."+
                    ".........."+
                    "......#..."+
                    "..#####..."+
                    "..#####..."+
                    "..#...#..."+
                    "..#...#..."+
                    "..#####..."+
                    ".........."+
                    ".#...#....";
    public static String aImg =
            ".........."+
                    ".........."+
                    ".........."+
                    ".........."+
                    ".........."+
                    ".........."+
                    ".........."+
                    "..#####..."+
                    ".........."+
                    "..........";

    public static void main(String[] args) {
        //Training
        String oLabelBinary = input2Binary(Trainingsdata.getOs().get(0));
        String xLabelBinary = input2Binary(Trainingsdata.getXs().get(0));
        String xLabelBinary2 = input2Binary(Trainingsdata.getXs().get(3));
        System.out.println(oLabelBinary);
        System.out.println(xLabelBinary);
        //Test environment
        for(int i = 0; i < Trainingsdata.getXs().size();i++) {
            System.out.println("\n+++++++++++++++++++++++++++++++++ "+i);
            String testBinary = input2Binary(Trainingsdata.getXs().get(i));
            Matrix testMatrix = binaryStr2Matrix(testBinary);
            System.out.println(testBinary);
            System.out.println(testMatrix);
            //Create Hopfieldnet
            ArrayList<String> binaryLabels = new ArrayList<String>();
            binaryLabels.add(oLabelBinary);
            binaryLabels.add(xLabelBinary);
            //binaryLabels.add(xLabelBinary2);
            HopfieldNet net = new HopfieldNet(binaryLabels);
            printClassification(net.classify(testMatrix));
        }
        System.out.println();
        System.out.println("Groesse der 0 Trainingsdaten: " +Trainingsdata.getOs().size());
        System.out.println("Groesse der X Trainingsdaten: "+ Trainingsdata.getXs().size());
    }


    public static String input2Binary(String input) {
        String binary = "";
        for(int i = 0; i < input.length();i++) {
            if(input.charAt(i)==' ' || input.charAt(i)=='\n') {
                continue;
            }
            if(input.charAt(i)=='#'){
                binary += "1";
            }
            else {
                binary += "0";
            }
        }
        return binary;
    }

    public static Matrix binaryStr2Matrix(String str) {
        Matrix m = new Matrix(1,100);
        for(int i = 0; i < str.length();i++) {
            if(str.charAt(i)=='1'){
                m.values[0][i] = 1;
            }
            else {
                m.values[0][i] = 0;
            }
        }
        return m.copy();
    }

    public static void printClassification(Matrix m) {
        for(int y = 0; y < m.ROWS; y++) {
            for(int x = 0; x < m.COLS; x++) {
                if(x!=0&&x%10==0) {
                    System.out.println();
                }
                if(m.values[y][x]>0) {
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }

            }

        }
        System.out.println();
        for(int y = 0; y < m.ROWS; y++) {
            for(int x = 0; x < m.COLS; x++) {
                if(x!=0&&x%10==0) {
                    System.out.println();
                }
                System.out.print((int)m.values[y][x]+" ");


            }

        }
    }

}
