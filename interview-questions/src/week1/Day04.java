package week1;

/**
 * Created by Rick on 10/27/16.
 */
public class Day04 {

    public static void main(String[] args) {
        System.out.println("---- Zero Matrix ----");
        int[][] m1 = {
                new int[] { 1, 2, 3 },
                new int[] { 4, 0, 6 },
                new int[] { 7, 0, 9 },
        };
        int[][] m2 = {
                new int[] { 0, 4, 9, 1},
                new int[] { 1, 5, 3, 7},
                new int[] { 2, 0, 4, 6}
        };

        doZeroMatrix(m1);
        doZeroMatrix(m2);

        System.out.println("---- String Rotation ----");
        checkIsRotation("flabbergasted", "abbergastedfl");
        checkIsRotation("tlebot", "bottle");
        checkIsRotation("flog", "golf");
    }

    /* 1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its   *
     *                  entire row and column are set to 0                                      */

    /**
     * Expands all zeroes to take up their entire row and column. This operations is in place, however
     * it's overall efficiency isn't so great.
     *
     * O((n*m) + (n-1)^2 + (m-1)^2 + n + m) = O((n-1)^2 + (m-1)^2)
     *
     * @param matrix
     */
    static void expandZeroes(int[][] matrix) {
        // Find zeroes, copy them to the first col/row to mark which need to be zeroed out.
        // Also, take note if there is a preexisting 0 in the first row or column
        boolean firstCol = false, firstRow = false;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    if (row == 0)
                        firstRow = true;
                    if (col == 0)
                        firstCol = true;
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // Look at the first row for zeroes, setting the columns for a zero entry to 0
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        // Look at the first col for zeroes, setting the rows for a zero entry to 0
        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }

        // Finally set the first column and row to zero if they have a zero entry.
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRow) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

    }

    static String formatMatrix(int[][] matrix) {
        StringBuilder builder = new StringBuilder(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            StringBuilder row = new StringBuilder(matrix[i].length);
            for (int j = 0; j < matrix[i].length; j++) {
                row.append(matrix[i][j]);
                row.append(" ");
            }
            builder.append(row);
            builder.append("\n");
        }

        return builder.toString();
    }

    static void doZeroMatrix(int[][] matrix) {
        System.out.println(formatMatrix(matrix));
        expandZeroes(matrix);
        System.out.println(formatMatrix(matrix));
    }

    /* 1.9 String Rotation: Determine if a string is a rotation of another string using only one call   *
     *                      too `isSubString`.                                                          */

    /**
     * Determine if a string is a rotation of another. This can be easily solved by concatenating
     * the string to check against with itself, and determining if the second is a substring.
     * For example: bottle, is ttlebo a rotation? -> bottlebottle clearly contains ttlebo, therefore it is!
     *
     * O(n^2), where n is the length of the string. String concatenation is n^2
     *
     * @param s1
     * @param s2
     * @return
     */
    static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        return isSubstring(s1.concat(s2), s2);
    }

    static boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }

    static void checkIsRotation(String s1, String s2) {
        System.out.printf("'%s' & '%s' -> %s\n", s1, s2, isRotation(s1, s2) ? "true" : "false");
    }
}
