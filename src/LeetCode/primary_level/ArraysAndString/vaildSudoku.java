package LeetCode.primary_level.ArraysAndString;

import java.util.HashSet;

public class vaildSudoku {
    public boolean isValidSudoku(char[][] board) {

        for (int i=0;i<9;i++){
            HashSet<Character> row = new HashSet<Character>();
            HashSet<Character> column = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j=0;j<9;j++){
                if (board[i][j] != '.' && !row.add(board[i][j]))return false;
                if (board[j][i] != '.' && !column.add(board[j][i]))return  false;
                int RowIndex = 3 * (i / 3) + j / 3;
                int ColIndex = 3 * (i % 3) + j % 3;
                if (board[RowIndex][ColIndex] != '.'
                        && !cube.add(board[RowIndex][ColIndex]))
                    return false;
            }
        }
        return true;
    }
}
