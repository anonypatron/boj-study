package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RollTheDice {
    static StringBuilder sb = new StringBuilder();
    static int[][] matrix;
    static int[] dice = new int[7];
    static int N, M, X, Y, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int command = Integer.parseInt(st.nextToken());
            roll(command);
        }

        System.out.print(sb);
    }

    private static void roll(int command) {
        int nextX = X, nextY = Y;

        // top, bottom, left, right, front, back
        switch (command) {
            case 1: // 동
                nextY = Y + 1;
                if (checkRange(nextX, nextY)) {
                    int tmp = dice[1];
                    dice[1] = dice[3];
                    dice[3] = dice[2];
                    dice[2] = dice[4];
                    dice[4] = tmp;

                    copy(nextX, nextY);
                    sb.append(dice[1]).append('\n');
                }
                break;
            case 2: // 서
                nextY = Y - 1;
                if (checkRange(nextX, nextY)) {
                    int tmp = dice[1];
                    dice[1] = dice[4];
                    dice[4] = dice[2];
                    dice[2] = dice[3];
                    dice[3] = tmp;

                    copy(nextX, nextY);
                    sb.append(dice[1]).append('\n');
                }
                break;
            case 3: // 북
                nextX = X - 1;
                if (checkRange(nextX, nextY)) {
                    int tmp = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[2];
                    dice[2] = dice[6];
                    dice[6] = tmp;

                    copy(nextX, nextY);
                    sb.append(dice[1]).append('\n');
                }
                break;
            case 4: // 남
                nextX = X + 1;
                if (checkRange(nextX, nextY)) {
                    int tmp = dice[1];
                    dice[1] = dice[6];
                    dice[6] = dice[2];
                    dice[2] = dice[5];
                    dice[5] = tmp;

                    copy(nextX, nextY);
                    sb.append(dice[1]).append('\n');
                }
                break;
        }
    }

    private static void copy(int x, int y) {
        if (matrix[x][y] == 0) {
            matrix[x][y] = dice[2];
        }
        else {
            dice[2] = matrix[x][y];
            matrix[x][y] = 0;
        }
        X = x;
        Y = y;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
