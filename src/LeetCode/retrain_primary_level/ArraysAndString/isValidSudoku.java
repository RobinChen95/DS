package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.HashMap;
import java.util.HashSet;

public class isValidSudoku {
    //此解法即为最佳解法
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> hs = new HashSet<>();
        //检查board[i][i]相同的行与列
        for (int i = 0; i < board.length; i++) {
            //检查与board[i][]相同的行
            for (int j = 0; j < 9; j++) {
                if (hs.contains(board[i][j]) && (board[i][j] >= '0' & board[i][j] <= '9')) {
                    return false;
                } else hs.add(board[i][j]);
            }
            hs.clear();
            //检查与board[][i]相同的行
            for (int j = 0; j < 9; j++) {
                if (hs.contains(board[j][i]) && (board[j][i] >= '0' & board[j][i] <= '9')) {
                    return false;
                } else hs.add(board[j][i]);
            }
            hs.clear();
        }
        //检查九宫格
        for (int i = 0; i < 9; i++) {
            //检查第i个九宫格
            for (int j = 0; j < 9; j++) {
                if (board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] == '.') continue;
                if (hs.contains(board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3])) {
                    return false;
                } else hs.add(board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3]);
            }
            hs.clear();
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] a = {
                {'.', '.', '.', '8', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '6', '.', '.', '.', '.', '3', '.', '.'},
                {'7', '.', '.', '9', '6', '4', '1', '.', '.'},
                {'6', '.', '9', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '5', '.'},
                {'.', '.', '9', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '5'},
                {'.', '.', '1', '.', '.', '.', '.', '2', '.'}};
        isValidSudoku vs = new isValidSudoku();
        System.out.println(vs.isValidSudoku(a));
    }
}
