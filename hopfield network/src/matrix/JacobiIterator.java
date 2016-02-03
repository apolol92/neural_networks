package matrix;

/**
 * Created by apolol92 on 07.08.2015.
 * This class is a Jacobi Iterator.
 * It iterates n-times to approximate the x-values of the linear system of equations.
 */
public class JacobiIterator {
    /**
     * This method calulate a approximation of x..
     * Be careful.. if A isn't diagonally dominant, then you shouldn't use Jacobi Iterator.. (test it before)
     * @param A, matrix
     * @param b, column vector
     * @param n, amount of iterations
     * @return approximation of x as column vector
     */
    public static Matrix iterate(Matrix A, Matrix b, int n) {
        //This variable contains the piviot
        double piviot;
        //piviot position
        int piviotPos = 0;
        Matrix xOld = new Matrix(3,1);
        Matrix xNew = xOld.copy();
        Matrix tmpB = b.copy();
        //n Iterations:
        for(int i = 0; i < n; i++) {
            //Each row
            for(int r = 0; r < A.ROWS; r++) {
                //Set piviot for each row
                piviot = A.values[r][piviotPos];
                for(int c = 0; c < A.COLS; c++) {
                    //Not at the piviot-position..
                    if(c!=piviotPos) {
                        //b - each matrix element * x[c][0]
                        tmpB.values[r][0] -= A.values[r][c]*xOld.values[c][0];
                    }
                }
                //Calculate new approximation of x
                xNew.values[r][0] = tmpB.values[r][0] / piviot;
                //Increase piviotPos
                piviotPos++;
            }
            //Reset piviot position
            piviotPos = 0;
            //Reset tmpB
            tmpB = b.copy();
            //New now old:
            xOld = xNew.copy();
        }
        return xNew;
    }
}
