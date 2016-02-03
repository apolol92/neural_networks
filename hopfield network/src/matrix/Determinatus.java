package matrix;

/**
 * Created by apolol92 on 08.08.2015.
 * This class calculate the determinat of a given matrix
 */
public class Determinatus {

    /**
     * This method calculate the determinat with laplace
     * @param matrix
     * @return the determinant
     */
    public static double det_laplace(Matrix matrix) {
        if(matrix.ROWS==2&&matrix.COLS==2) {
            return matrix.values[0][0] * matrix.values[1][1] - matrix.values[0][1]*matrix.values[1][0];
        }
        else {
            //Laplace development after column 0
            double sum = 0;
            for(int r = 0; r < matrix.ROWS; r++) {
                Matrix child = generate_childMatrixFrom(matrix, r);
                if(r%2==0) {
                    sum+=matrix.values[r][0] * det_laplace(child);
                }
                else {
                    sum += (-1)*matrix.values[r][0] * det_laplace(child);
                }
            }
            return sum;
        }
    }

    /**
     * Create a new child matrix for the laplace method
     * @param m, parent matrix
     * @param r_parent, current row in column 0
     * @return the child matrix
     */
    private static Matrix generate_childMatrixFrom(Matrix m, int r_parent) {
        Matrix nMatrix = new Matrix(m.ROWS-1, m.COLS-1);
        int nc=0, nr=0;
        for(int r = 0; r < m.ROWS; r++) {
            if(r == r_parent) {
                continue;
            }
            for(int c = 1; c < m.COLS; c++) {
                nMatrix.values[nr][nc] = m.values[r][c];
                nc++;
            }
            nc = 0;
            nr++;
        }
        return nMatrix;
    }
}
