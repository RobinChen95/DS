package Chapter1_Basic;

public class searchMatrix {

    public static void main(String[] args) {
        searchMatrix sm = new  searchMatrix();
        int[][] matrix_test = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int number = 5;
        System.out.println(sm.searchMatrix(matrix_test,number));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        return false;
    }
}
