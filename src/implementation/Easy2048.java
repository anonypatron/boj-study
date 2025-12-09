package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Easy2048 { // 12100
    static class Point {
        int x, y, value;
        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                return this.value == ((Point) obj).value;
            }
            return false;
        }
    }
    static int N, max = 2;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.print(max);
    }

    private static void dfs(int[][] matrix, int depth) {
        if (depth == 5) {
            max = Math.max(maxValue(matrix), max);
            return;
        }

        dfs(up(matrix), depth + 1);
        dfs(right(matrix), depth + 1);
        dfs(down(matrix), depth + 1);
        dfs(left(matrix), depth + 1);
    }

    /*
        1. 세로줄에서 가장 위에 있는 숫자 하나를 잡는다.(x)
        2. 그 아래에 숫자를 찾는다.(y)
            2-1. x와 y가 같다면 tmp[index][j]에 x * 2를 넣는다
            2-2. x와 y가 다르면 a를 tmp[index][j]에 넣고 다음 숫자를 찾는다
        3. 끝까지 갔을 때 짝이 안맞으면 tmp[index][j]에 넣으면 끝.
        주의 사항 : 마지막에는 비어있는 공간에 0으로 채워야 함.
     */
    private static int[][] up(int[][] matrix) {
        int[][] tmp = copyMap(matrix);

        for (int i = 0; i < N; i++) { // 세로줄의 개수
            int index = 0; // 놓을 위치
            Point x = null, y;
            boolean isHold = false;

            for (int j = 0; j < N; j++) { // 가로로 몇 칸
                if (tmp[j][i] != 0) {
                    if (isHold) {
                        y = new Point(j, i, tmp[j][i]); // 두 번째 값 찾음

                        if (x.equals(y)) { // x와 y의 value가 같음
                            int storedValue = x.value * 2;
                            tmp[x.x][x.y] = 0;
                            tmp[y.x][y.y] = 0;

                            tmp[index++][i] = storedValue;
                            isHold = false;
                            x = null;
                            y = null;
                        }
                        else { // 다른 경우
                            tmp[x.x][x.y] = 0;
                            tmp[index++][i] = x.value;
                            x = new Point(y.x, y.y, y.value);
                            y = null;
                        }
                    }
                    else {
                        x = new Point(j, i, tmp[j][i]); // 첫 번째 값 찾음
                        isHold = true;
                    }
                }
            }

            if (isHold) {
                tmp[x.x][x.y] = 0;
                tmp[index][i] = x.value;
            }
        }
        return tmp;
    }

    private static int[][] right(int[][] matrix) {
        int[][] tmp = copyMap(matrix);

        for (int i = 0; i < N; i++) {
            int index = N - 1;
            Point x = null, y;
            boolean isHold = false;

            for (int j = N - 1; j >= 0; j--) {
                if (tmp[i][j] != 0) {
                    if (isHold) {
                        y = new Point(i, j, tmp[i][j]);

                        if (x.equals(y)) {
                            int storedValue = x.value * 2;
                            tmp[x.x][x.y] = 0;
                            tmp[y.x][y.y] = 0;

                            tmp[i][index--] = storedValue;
                            isHold = false;
                            x = null;
                            y = null;
                        }
                        else {
                            tmp[x.x][x.y] = 0;
                            tmp[i][index--] = x.value;
                            x = new Point(y.x, y.y, y.value);
                            y = null;
                        }
                    }
                    else {
                        x = new Point(i, j, tmp[i][j]);
                        isHold = true;
                    }
                }
            }

            if (isHold) {
                tmp[x.x][x.y] = 0;
                tmp[i][index] = x.value;
            }
        }
        return tmp;
    }

    private static int[][] down(int[][] matrix) {
        int[][] tmp = copyMap(matrix);

        for (int i = 0; i < N; i++) {
            int index = N - 1; // 놓을 위치
            Point x = null, y;
            boolean isHold = false;

            for (int j = N - 1; j >= 0; j--) {
                if (tmp[j][i] != 0) {
                    if (isHold) {
                        y = new Point(j, i, tmp[j][i]);

                        if (x.equals(y)) {
                            int storedValue = x.value * 2;
                            tmp[x.x][x.y] = 0;
                            tmp[y.x][y.y] = 0;

                            tmp[index--][i] = storedValue;
                            isHold = false;
                            x = null;
                            y = null;
                        }
                        else {
                            tmp[x.x][x.y] = 0;
                            tmp[index--][i] = x.value;
                            x = new Point(y.x, y.y, y.value);
                            y = null;
                        }
                    }
                    else {
                        x = new Point(j, i, tmp[j][i]);
                        isHold = true;
                    }
                }
            }

            if (isHold) {
                tmp[x.x][x.y] = 0;
                tmp[index][i] = x.value;
            }
        }
        return tmp;
    }

    private static int[][] left(int[][] matrix) {
        int[][] tmp = copyMap(matrix);

        for (int i = 0; i < N; i++) {
            int index = 0;
            Point x = null, y;
            boolean isHold = false;

            for (int j = 0; j < N; j++) {
                if (tmp[i][j] != 0) {
                    if (isHold) {
                        y = new Point(i, j, tmp[i][j]);

                        if (x.equals(y)) {
                            int storedValue = x.value * 2;
                            tmp[x.x][x.y] = 0;
                            tmp[y.x][y.y] = 0;

                            tmp[i][index++] = storedValue;
                            isHold = false;
                            x = null;
                            y = null;
                        }
                        else {
                            tmp[x.x][x.y] = 0;
                            tmp[i][index++] = x.value;
                            x = new Point(y.x, y.y, y.value);
                            y = null;
                        }
                    }
                    else {
                        x = new Point(i, j, tmp[i][j]);
                        isHold = true;
                    }
                }
            }

            if (isHold) {
                tmp[x.x][x.y] = 0;
                tmp[i][index] = x.value;
            }
        }
        return tmp;
    }

    private static int[][] copyMap(int[][] matrix) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    private static int maxValue(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, matrix[i][j]);
            }
        }
        return max;
    }

}
