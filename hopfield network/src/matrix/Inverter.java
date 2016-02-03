package matrix;

/**
 * Created by apolol92 on 08.08.2015.
 * This class invertes matrices.
 */
public class Inverter {

    /**
     * This method do the main job.. it invertes matrices
     * @param A is the matrix
     * @return the inverted matrix
     */
    public static Matrix inverte(Matrix A) {
        Matrix identityMatrix = A.getIdentityMatrix();
        Matrix inverse = new Matrix(1,1);
        for(int identityColumn = 0; identityColumn < identityMatrix.COLS; identityColumn++) {
            Matrix eVec = identityMatrix.getColumnVector(identityColumn);
            if(A.diagonallyDominant()!=2) {
                //LU Decomposition
                return LUDecomposition.decomposition(A,eVec);
            }
            Matrix xn = JacobiIterator.iterate(A,eVec,100); //A*xn = eVec
            System.out.println(A);
            System.out.println(eVec);
            System.out.println(xn);
            switch (identityColumn) {
                case 0:
                    inverse = xn;
                    break;
                default:
                    inverse = inverse.concatHorizontal(xn);
                    break;
            }
        }
        return inverse;
    }

    /**
     * This method create the inverse of a frobenius matrix.
     * @param matrix, must be a frobenius matrix
     * @return
     */
    public static Matrix FrobeniusInvert(Matrix matrix) {
        Matrix inverse = matrix.copy();
        for(int r = 0; r < matrix.ROWS; r++) {
            for(int c = 0; c < matrix.COLS; c++) {
                if(r!=c) {
                    inverse.values[r][c] *= (-1);
                }
            }
        }

        return inverse;
    }
}
