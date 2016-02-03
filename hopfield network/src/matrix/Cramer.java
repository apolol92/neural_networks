package matrix;

/**
 * Created by apolol92 on 31.10.2015.
 * This class calculates a linear equation system by using cramer method..
 * This method isn't very fast..
 */
public class Cramer {
    /**
     * Solvese the linear equation system by using cramer method
     * @param m, a matrix
     * @return a vector as a matrix with the solution from x1 to xn
     */
    public static Matrix calc_linear_equation_system(Matrix m) {
        //TODO: if detA = 0.. EXCEPTION..
        Matrix a = create_a(m);
        Matrix b = create_b(m);
        Matrix x = new Matrix(m.ROWS,1);   //For result
        double detA = a.getDeterminant();
        if(detA==0) {
            return null;
        }
        for(int i = 0; i < m.COLS-1; i++) {
            //Replace coli with b:
            Matrix ai = create_a_i(a.copy(),b.copy(),i);
            x.values[i][0] = ai.getDeterminant()/detA;
        }
        return x;
    }

    /**
     * Creates the a matrix
     * @param m
     * @return
     */
    private static Matrix create_a(Matrix m) {
        Matrix a = new Matrix(m.ROWS,m.COLS-1);
        for(int y = 0; y < m.ROWS; y++) {
            for(int x = 0; x < m.COLS-1; x++) {
                a.values[y][x] = m.values[y][x];
            }
        }
        return a;
    }

    /**
     * Creates the b matrix
     * @param m
     * @return
     */
    private static Matrix create_b(Matrix m) {
        Matrix b = new Matrix(m.ROWS,1);
        for(int y = 0; y < m.ROWS; y++) {
            b.values[y][0] = m.values[y][m.COLS-1];
        }
        return b;
    }

    /**
     * Creates the Ai matrix
     * @param a
     * @param b
     * @param i
     * @return
     */
    private static Matrix create_a_i(Matrix a, Matrix b, int i) {
        Matrix ai = new Matrix(a.ROWS, a.COLS);
        ai = a.copy();
        for(int y = 0; y < a.ROWS; y++) {
            ai.values[y][i] = b.values[y][0];
        }
        return ai;
    }
}
