package implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FineDust { // 17144
    static int[][] matrix, tmp;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
    static int rx, ry; // 로봇의 하단 좌표를 나타냄
    static int N, M, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == -1) {
                    rx = i;
                    ry = j;
                }
            }
        }

        while (T-- > 0) {
            tmp = new int[N][M];
            diffusion();
            act();
        }

        System.out.print(countAmount());
    }

    // 바로바로 계산하면 연쇄적으로 일어남.
    // 임시로 계산한 배열(tmp)을 하나 두고 끝나면 더하기
    private static void diffusion() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (matrix[x][y] > 0) {
                    int count = 0; // 주변에 몇 칸이 퍼지는 지 카운트
                    int amount = matrix[x][y] / 5;

                    for (int i = 0; i < 4; i++) {
                        int newX = x + dx[i];
                        int newY = y + dy[i];

                        if (checkRange(newX, newY) && matrix[newX][newY] != -1) {
                            tmp[newX][newY] += amount;
                            count++;
                        }
                    }

                    matrix[x][y] -= (count * amount);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] += tmp[i][j];
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static void top() {
        int robotX = rx - 1, robotY = ry;

        for (int i = robotX - 2; i >= 0; i--) {
            matrix[i + 1][0] = matrix[i][0];
            matrix[i][0] = 0;
        }

        for (int i = 1; i < M; i++) {
            matrix[0][i - 1] = matrix[0][i];
            matrix[0][i] = 0;
        }

        for (int i = 0; i < robotX; i++) {
            matrix[i][M - 1] = matrix[i + 1][M - 1];
            matrix[i + 1][M - 1] = 0;
        }

        for (int i = M - 1; i > 1; i--) {
            matrix[robotX][i] = matrix[robotX][i - 1];
            matrix[robotX][i - 1] = 0;
        }
        matrix[robotX][1] = 0;
    }

    private static void bottom() {
        int robotX = rx, robotY = ry;

        for (int i = robotX + 2; i < N; i++) {
            matrix[i - 1][0] = matrix[i][0];
            matrix[i][0] = 0;
        }

        for (int i = 1; i < M; i++) {
            matrix[N - 1][i - 1] = matrix[N - 1][i];
            matrix[N - 1][i] = 0;
        }

        for (int i = N - 1; i > robotX; i--) {
            matrix[i][M - 1] = matrix[i - 1][M - 1];
            matrix[i - 1][M - 1] = 0;
        }

        for (int i = M - 1; i > 1; i--) {
            matrix[robotX][i] = matrix[robotX][i - 1];
            matrix[robotX][i - 1] = 0;
        }
        matrix[robotX][1] = 0;
    }

    private static void act() {
        top();
        bottom();
    }

    private static int countAmount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] > 0) {
                    count += matrix[i][j];
                }
            }
        }
        return count;
    }

}
