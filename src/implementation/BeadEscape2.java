package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BeadEscape2 {
    /*
        왼쪽, 오른쪽, 위, 아래로 기울이기 메서드 만들기
        빨간 구슬이 구멍에 빠졌는지 확인하기
        파란 구슬이 구멍에 빠졌는지 확인하기
        bfs로 모든 경우의 수를 찾은 후 최소값을 정적변수에 담아 출력하기
     */
    static class Matrix {
        char[][] map;
        int depth;
        public Matrix(char[][] map, int depth) {
            this.map = map;
            this.depth = depth;
        }
    }
    static int N, M;
    static char[][] originMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        originMatrix = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                originMatrix[i][j] = line.charAt(j);
            }
        }

        System.out.print(bfs(originMatrix));
    }

    private static int bfs(char[][] matrix) {
        Deque<Matrix> deque = new ArrayDeque<>();
        deque.addLast(new Matrix(matrix, 0));

        while (!deque.isEmpty()) {
            Matrix current = deque.pollFirst();

            if (current.depth == 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                char[][] tmp = deepCopy(current.map);
                int status;
                char[][] after;

                switch (i) {
                    case 0:
                        after = up(tmp);
                        status = check(after);

                        if (status == 1) { // 끝남
                            return current.depth + 1;
                        }
                        if (status == -1) { // 둘 다 안들어간 상태
                            deque.addLast(new Matrix(after, current.depth + 1));
                        }
                        break;
                    case 1:
                        after = right(tmp);
                        status = check(after);

                        if (status == 1) { // 끝남
                            return current.depth + 1;
                        }
                        if (status == -1) { // 둘 다 안들어간 상태
                            deque.addLast(new Matrix(after, current.depth + 1));
                        }
                        break;
                    case 2:
                        after = down(tmp);
                        status = check(after);

                        if (status == 1) { // 끝남
                            return current.depth + 1;
                        }
                        if (status == -1) { // 둘 다 안들어간 상태
                            deque.addLast(new Matrix(after, current.depth + 1));
                        }
                        break;
                    case 3:
                        after = left(tmp);
                        status = check(after);

                        if (status == 1) { // 끝남
                            return current.depth + 1;
                        }
                        if (status == -1) { // 둘 다 안들어간 상태
                            deque.addLast(new Matrix(after, current.depth + 1));
                        }
                        break;
                }
            }
        }
        return -1;
    }

    private static char[][] up(char[][] matrix) {
        for  (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (matrix[i][j] == 'R' || matrix[i][j] == 'B') {
                    int k = i;
                    boolean isGoal = false;

                    while (matrix[k - 1][j] != '#') {
                        if (matrix[k - 1][j] == 'O') {
                            matrix[i][j] = '.';
                            isGoal = true;
                        }
                        if (matrix[k - 1][j] == 'R' || matrix[k - 1][j] == 'B') {
                            break;
                        }
                        k--;
                    }

                    if (!isGoal && i != k) {
                        matrix[k][j] = matrix[i][j];
                        matrix[i][j] = '.';
                    }
                }
            }
        }
        return matrix;
    }

    private static char[][] right(char[][] matrix) {
        for  (int i = 1; i < N - 1; i++) {
            for (int j = M - 2; j > 0; j--) {
                if (matrix[i][j] == 'R' || matrix[i][j] == 'B') {
                    int k = j;
                    boolean isGoal = false;

                    while (matrix[i][k + 1] != '#') {
                        if (matrix[i][k + 1] == 'O') {
                            matrix[i][j] = '.';
                            isGoal = true;
                        }
                        if (matrix[i][k + 1] == 'R' || matrix[i][k + 1] == 'B') {
                            break;
                        }
                        k++;
                    }

                    if (!isGoal && j != k) {
                        matrix[i][k] = matrix[i][j];
                        matrix[i][j] = '.';
                    }
                }
            }
        }
        return matrix;
    }

    private static char[][] down(char[][] matrix) {
        for  (int i = N - 2; i > 0; i--) {
            for (int j = 1; j < M - 1; j++) {
                if (matrix[i][j] == 'R' || matrix[i][j] == 'B') {
                    int k = i;
                    boolean isGoal = false;

                    while (matrix[k + 1][j] != '#') {
                        if (matrix[k + 1][j] == 'O') {
                            matrix[i][j] = '.';
                            isGoal = true;
                        }
                        if (matrix[k + 1][j] == 'R' || matrix[k + 1][j] == 'B') {
                            break;
                        }
                        k++;
                    }

                    if (!isGoal && i != k) {
                        matrix[k][j] = matrix[i][j];
                        matrix[i][j] = '.';
                    }
                }
            }
        }
        return matrix;
    }

    private static char[][] left(char[][] matrix) {
        for  (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (matrix[i][j] == 'R' || matrix[i][j] == 'B') {
                    int k = j;
                    boolean isGoal = false;

                    while (matrix[i][k - 1] != '#') {
                        if (matrix[i][k - 1] == 'O') {
                            matrix[i][j] = '.';
                            isGoal = true;
                        }
                        if (matrix[i][k - 1] == 'R' || matrix[i][k - 1] == 'B') {
                            break;
                        }
                        k--;
                    }
                    if (!isGoal && j != k) {
                        matrix[i][k] = matrix[i][j];
                        matrix[i][j] = '.';
                    }
                }
            }
        }
        return matrix;
    }

    private static int check(char[][] matrix) {
        if (checkRed(matrix) && checkBlue(matrix)) { // 빨강만 들어왔을 때 끝
            return 1;
        }
        if (!checkBlue(matrix)) { // 파랑이 들어오면 끝
            return 0;
        }
        return -1; // 둘 다 못들어온 상태(더 지켜봐야함)
    }

    private static boolean checkRed(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'R') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBlue(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'B') {
                    return true;
                }
            }
        }
        return false;
    }

    private static char[][] deepCopy(char[][] matrix) {
        char[][] copy = new char[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

}
