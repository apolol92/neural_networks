package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolol92 on 09.08.2015.
 * This class calculate the solution of a linear system of equations via LU Decomposition..
 */
public class LUDecomposition {

    /**
     * Use this method to calculate A*x=b
     * @param matrixA
     * @param b
     * @return x
     */
    public static Matrix decomposition(Matrix matrixA, Matrix b) {
        List<Matrix> listLs = new ArrayList<Matrix>();
        Matrix A = matrixA.copy();
        Matrix R;
        //TODO:CHECK FOR NXN MATRIX A
        //Calculate R
        for(int c = 0,n=0; c < A.COLS-1; c++,n++) {
            Matrix L = new Matrix(matrixA.ROWS,matrixA.COLS);
            L = L.getIdentityMatrix();
            for(int r = n+1,anchor=n; r < A.ROWS;r++) {
                if(A.values[r][c]!=0) {
                    double destroyer = getDestroyer(A.values[anchor][c], A.values[r][c]);
                    L.values[r][c] = destroyer;
                }
            }
            //Save L
            listLs.add(L.copy());
            A = L.multiply(A);
        }
        R = A;
        //Calculate L
        Matrix L = new Matrix(matrixA.ROWS,matrixA.COLS);
        for(int i = 0; i < listLs.size(); i++) {
            switch(i) {
                case 0:
                    L = Inverter.FrobeniusInvert(listLs.get(i));
                    break;
                default:
                    L = L.multiply(Inverter.FrobeniusInvert(listLs.get(i)));
                    break;
            }



        }
        Matrix y = calculateY(L,b);
        Matrix x = calculateX(R, y);
        return x;
    }

    /**
     * Support method for eliminate element n+1 with element n
     * @param num1 element n
     * @param num2 element n+1
     * @return factor witch eliminate elment n+1
     */
    private static double getDestroyer(double num1, double num2) {
        return (-1d)*num2/num1;
    }

    /**
     * This method calculate L*y = b
     * @param L
     * @param b
     * @return
     */
    private static Matrix calculateY(Matrix L, Matrix b) {
        Matrix y = new Matrix(b.ROWS,1);
        for(int r = 0; r < L.ROWS; r++) {
           if(r==0) {
               y.values[r][0] = b.values[r][0]/L.values[r][0];
           }
            else {
               double value = 0;
               for(int c = 0; c < r; c++) {
                    value+=L.values[r][c]*y.values[c][0];
               }
               y.values[r][0] = (b.values[r][0]-value)/L.values[r][r];
           }
        }
       return y;
    }

    /**
     * This method calculate R*x=y
     * @param R
     * @param y
     * @return
     */
    private static Matrix calculateX(Matrix R, Matrix y) {
        Matrix x = new Matrix(y.ROWS,1);
        for(int r = R.ROWS-1; r>=0;r--) {
            if(r == R.ROWS-1) {
                x.values[r][0] = y.values[r][0]/R.values[r][r];
            }
            else {
                double value = 0;
                for(int c = R.COLS-1; c > r; c--) {
                    value += R.values[r][c]*x.values[c][0];
                }
                y.values[r][0]-=value;
                x.values[r][0] = y.values[r][0] / R.values[r][r];
            }
        }
        return x;
    }
}
