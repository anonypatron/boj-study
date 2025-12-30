import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main { // 2447
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '*';
            }
        }

        recur(0, 0, n);
        Arrays.stream(board)
                .forEach(System.out::println);
    }

    private static void recur(int x, int y, int size) {
        if (size == 1) {
            return;
        }

        int oneThird = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) { // 가운데
                    for (int dx = x + oneThird; dx < x + (oneThird * 2); dx++) {
                        for (int dy = y + oneThird; dy < y + (oneThird * 2); dy++) {
                            board[dx][dy] = ' ';
                        }
                    }
                }
                else {
                    recur(x + i * oneThird, y + j * oneThird, oneThird);
                }
            }
        }
    }

}
