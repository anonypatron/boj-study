package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen { // 9663
    static int result, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        boolean[][] matrix = new boolean[N][N];

        recur(matrix, 0);
        System.out.print(result);
    }

    private static void recur(boolean[][] matrix, int depth) {
        if (depth == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isPossible(matrix, depth, i)) {
                matrix[depth][i] = true;
                recur(matrix, depth + 1);
                matrix[depth][i] = false;
            }
        }
    }

    private static boolean isPossible(boolean[][] board, int row, int col) {
        // 세로
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // 대각선
        int leftUpRow = row - 1, leftUpCol = col - 1;
        while (leftUpRow >= 0 && leftUpCol >= 0) {
            if (board[leftUpRow][leftUpCol]) {
                return false;
            }
            leftUpRow--;
            leftUpCol--;
        }

        int rightUpRow = row - 1, rightUpCol = col + 1;
        while (rightUpRow >= 0 && rightUpCol < N) {
            if (board[rightUpRow][rightUpCol]) {
                return false;
            }
            rightUpRow--;
            rightUpCol++;
        }

        return true;
    }

}
