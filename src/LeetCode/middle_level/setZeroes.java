package LeetCode.middle_level;

import java.util.ArrayList;
import java.util.List;

public class setZeroes {

    private class position {
        int col;
        int row;

        public position(int row, int col) {
            this.col = col;
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }
    }

    public void setZeroes(int[][] matrix) {
        List<position> al = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]==0)al.add(new position(i,j));
            }
        }
        for (int i = 0; i < al.size(); i++) {
            position po = al.get(i);
            // 行置零
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[po.getRow()][j]=0;
            }
            // 列置零
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][po.getCol()]=0;
            }
        }
    }

    public static void main(String[] args) {
        setZeroes sz = new setZeroes();
        int[][] test_mat = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        sz.setZeroes(test_mat);
        for (int i = 0; i < test_mat.length; i++) {
            for (int j = 0; j < test_mat[i].length; j++) {
                System.out.print(test_mat[i][j]);
            }
            System.out.println();
        }
    }
}
