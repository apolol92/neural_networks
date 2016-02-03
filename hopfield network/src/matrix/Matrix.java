package matrix;

import java.util.Arrays;

/**
 * Created by apolol92 on 07.08.2015.
 * This class is the basic structure of the library.
 * It is a mathematical matrix.
 * It should contain all matrix operations..
 */
public class Matrix {
    /**
     * This 2d-array contains all matrix-elements
     */
    public double[][] values;
    /**
     * This to constants show us the dimension of the matrix
     */
    public final int ROWS, COLS;

    /**
     * This constructor creates a matrix
     * @param rows, amount of rows
     * @param cols, amount of cols
     */
    public Matrix(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        this.values = new double[this.ROWS][this.COLS];
    }

    public Matrix(String str) {
        Matrix nMatrix = StrMatrixParser.createMatrixFrom(str);
        this.ROWS = nMatrix.ROWS;
        this.COLS = nMatrix.COLS;
        this.values = nMatrix.copyValues();
    }

    /**
     * This method returns a print of the matrix
     * @return nice matrix print
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("");
        for(int r = 0; r < this.ROWS; r++) {
            for(int c = 0; c < this.COLS; c++) {
                if(c==this.COLS-1) {
                    output.append(this.values[r][c]+"\n");
                }
                else {
                    output.append(this.values[r][c]+" ");
                }
            }
        }
        return output.toString();
    }

    /**
     * This method creates a copy of the current matrix
     * @return the copy
     */
    public Matrix copy() {
        Matrix copy = new Matrix(this.ROWS,this.COLS);
        for(int r = 0; r < this.ROWS; r++) {
            copy.values[r] = this.values[r].clone();
        }
        return copy;
    }

    /**
     * This method add the matrix elements on the own matrix elements.
     * In other words, this method make an addition and same the result in its own values.
     * @param matrix
     * @return a reference on itself
     */
    public Matrix self_addition(Matrix matrix) {
        try {
            checkSameMatrixDimension(this,matrix);
            for(int r = 0; r < this.ROWS; r++) {
                for(int c = 0; c < this.COLS; c++) {
                    this.values[r][c] += matrix.values[r][c];
                }
            }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return this;
    }

    /**
     * This method make an addition and save the result in a extra matrix.
     * @param matrix, summand
     * @return result
     */
    public Matrix addition(Matrix matrix) {
        Matrix result = null;
        try {
            checkSameMatrixDimension(this,matrix);
            result = new Matrix(this.ROWS,this.COLS);
            for(int r = 0; r < this.ROWS; r++) {
                for(int c = 0; c < this.COLS; c++) {
                    result.values[r][c] = this.values[r][c] + matrix.values[r][c];
                }
            }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * This method make a subtraction and save the result in its own values.
     * @param matrix, operand
     * @return result
     */
    public Matrix self_subtraction(Matrix matrix) {
        try {
            checkSameMatrixDimension(this,matrix);
            for(int r = 0; r < this.ROWS; r++) {
                for(int c = 0; c < this.COLS; c++) {
                    this.values[r][c] -= matrix.values[r][c];
                }
            }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return this;
    }
    /**
     * This method make a subtraction and save the result in a extra matrix.
     * @param matrix, operand
     * @return result
     */
    public Matrix subtraction(Matrix matrix) {
        Matrix result = null;
        try {
            checkSameMatrixDimension(this, matrix);
            result = new Matrix(this.ROWS,this.COLS);
            for(int r = 0; r < this.ROWS; r++) {
                for(int c = 0; c < this.COLS; c++) {
                    result.values[r][c] = this.values[r][c] - matrix.values[r][c];
                }
            }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Multiply current matrix with matrix2
     * @param matrix2, other matrix
     * @return result of multiplication
     */
    public Matrix multiply(Matrix matrix2) {
        Matrix result = null;
        try {
            checkMultiplyCondition(this, matrix2);
            result = new Matrix(this.ROWS,matrix2.COLS);
           for(int r1 = 0; r1 < this.ROWS; r1++) {
               for(int c2 = 0;c2<matrix2.COLS;c2++) {
                   double sum = 0;
                   for(int c1=0; c1 < this.COLS;c1++) {
                       sum += this.values[r1][c1]*matrix2.values[c1][c2];
                   }
                   result.values[r1][c2] = sum;
               }
           }
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * This method checks the dimension of m1 and m2.
     * It throws a Exception, if their dimension(m1 and m2) aren't equal.
     * @param m1, Matrix1
     * @param m2, Matrix2
     * @throws Exception dimensions aren't equal..
     */
    private void checkSameMatrixDimension(Matrix m1, Matrix m2) throws Exception{
        if(m1.ROWS != m2.ROWS || m1.COLS != m2.COLS) {
            throw new Exception("matrices haven't got the same dimension..");
        }
    }

    /**
     * Is it possible to multiply those two matrices?
     * @param m1, matrix1
     * @param m2, matrix2
     * @throws Exception
     */
    private void checkMultiplyCondition(Matrix m1, Matrix m2) throws  Exception {
        if(m1.COLS != m2.ROWS) {
            throw new Exception("matrices haven't got the right dimension for multiplication..");
        }
    }

    /**
     * This method creates the identity matrix
     * @return the identity matrix
     */
    public Matrix getIdentityMatrix() {
        Matrix identity = new Matrix(this.ROWS, this.COLS);
        for(int r = 0; r < this.ROWS; r++) {
            for(int c = 0; c < this.COLS;c++) {
                if(r==c) {
                    identity.values[r][c] = 1;
                }
                else {
                    identity.values[r][c] = 0;
                }
            }
        }
        return identity;
    }

    /**
     * This method return the column vector in [column] column
     * @param column, which column(started with 0)
     * @return the column vector
     */
    public Matrix getColumnVector(int column) {
        Matrix columnVector = new Matrix(this.ROWS,1);
        for(int r = 0; r < this.ROWS; r++) {
            columnVector.values[r][0] = this.values[r][column];
        }
        return columnVector;
    }

    /**
     * This method concat current und parameter matrix together and return the result
     * @param matrix, which matrix should concated to current matrix
     * @return the concated matrix
     */
    public Matrix concatHorizontal(Matrix matrix) {
        //TODO: CHECK DIMENSIONS!!!
        Matrix nMatrix = new Matrix(this.ROWS,this.COLS+matrix.COLS);
        //Put current matrix in nMatrix
        for(int r = 0; r < this.ROWS; r++) {
            for(int c = 0; c < this.COLS;c++) {
                nMatrix.values[r][c] = this.values[r][c];
            }
        }
        //Put m2 in nMatrix
        int m2c = 0;
        for(int r = 0; r < matrix.ROWS; r++) {
            for(int c = this.COLS; c < this.COLS+matrix.COLS;c++) {
                nMatrix.values[r][c] = matrix.values[r][m2c];
                m2c++;
            }
            m2c = 0;
        }
        return nMatrix;
    }

    /**
     * Is current matrix diagonally dominant?
     * @return typ = 0 if not, typ = 1 if weak diagonally dominant, typ = 2 if strict diagonally dominant
     */
    public int diagonallyDominant() {
        int typ = 2;
        for(int r = 0; r < this.ROWS; r++) {
            double sum = 0;
            for(int c = 0; c < this.COLS;c++) {
                if(r!=c) {
                    sum += Math.abs(this.values[r][c]);
                }
            }
            if(sum<Math.abs(this.values[r][r])) {
                typ = 2;
            }
            else if(sum<=Math.abs(this.values[r][r])) {
                typ = 1;
            }
            else {
                typ = 0;
                break;
            }

        }
        return typ;
    }

    /**
     * Copy the values from this matrix
     * @return
     */
    public double[][] copyValues() {
        double[][] cValues = new double[this.ROWS][this.COLS];
        for(int r = 0; r < this.ROWS; r++) {
            for(int c = 0; c < this.COLS; c++) {
                cValues[r][c] = this.values[r][c];
            }
        }
        return cValues;
    }

    public double getDeterminant() {
        return Determinatus.det_laplace(this);
    }
}
