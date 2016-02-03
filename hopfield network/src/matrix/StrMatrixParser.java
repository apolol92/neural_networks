package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apolol92 on 31.10.2015.
 * This class holds static methods to parse matrices from strings
 */
public class StrMatrixParser {

    /**
     * This method creates a matrix from a string.
     * Till now it only supports csv-strings
     * @param str as a csv-string
     * @return matrix
     */
    public static Matrix createMatrixFrom(String str) {
        Matrix newMatrix = fromCsvStr(str);
        return newMatrix;
    }

    /**
     * Creates a matrix from a csv-string
     * @param str
     * @return
     */
    private static Matrix fromCsvStr(String str)  {
        Matrix mat = null;
        List<String> rows = csv_getRows(str);
        if(rows.size()>0) {
            for(int i = 0; i < rows.size(); i++) {
                List<Double> nums = csv_getNumsInRow(rows.get(i));
                if(i==0) {
                    mat = new Matrix(rows.size(),csv_maxCols(rows));
                }
                for(int x = 0; x < nums.size(); x++) {
                    mat.values[i][x] = nums.get(x);
                }
            }

            return mat;
        }
        else {
            return mat;
        }
    }

    /**
     * Count the amount of columns in each row..
     * @param rows
     * @return
     */
    private static int csv_maxCols(List<String> rows) {
        int max_cols = 0;
        for(int i = 0; i < rows.size();i++) {
            int pos = 0, oldpos=0;
            int count = 0;
            while(true) {
                oldpos = pos;
                pos = rows.get(i).indexOf(",",oldpos);
                if(pos == -1) {
                    count++;
                    break;
                }
                pos++;
                count++;
            }
            if(count>max_cols) {
                max_cols = count;
            }
        }
        return max_cols;
    }

    /**
     * Get every row from the total string
     * @param str
     * @return
     */
    private static List<String> csv_getRows(String str) {
        int pos = 0, oldpos=0;
        List<String> rows = new ArrayList<String>();
        while(true) {
            oldpos = pos;
            pos = str.indexOf(";",oldpos);
            if(pos == -1) {
                break;
            }
            pos++;
            rows.add(str.substring(oldpos,pos-1));

        }
        return rows;
    }

    /**
     * Get the nums in each row
     * @param row
     * @return
     */
    private static List<Double> csv_getNumsInRow(String row) {
        List<Double> nums = new ArrayList<Double>();
        int pos = 0, oldpos = 0;
        while(true) {
            oldpos = pos;
            pos = row.indexOf(",",oldpos);
            if(pos==-1) {
                nums.add(Double.parseDouble(row.substring(oldpos, row.length())));
                return nums;
            }
            pos++;
            nums.add(Double.parseDouble(row.substring(oldpos, pos-1)));

        }
    }
}
