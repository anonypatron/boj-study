package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Supervise {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Cctv {
        Point point;
        char value;
        public Cctv(Point point, char value) {
            this.point = point;
            this.value = value;
        }
    }
    static int[] dx = { 0, 1, 2, 3 }; // 북, 동, 남, 서
    static char[][] originMatrix;
    static int N, M, MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originMatrix = new char[N][M];

        List<Cctv> cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                char x = st.nextToken().charAt(0);

                if ('0' < x && x < '6') {
                    cctvs.add(new Cctv(new Point(i, j), x));
                }
                originMatrix[i][j] = x;
            }
        }

        dfs(originMatrix, cctvs, 0);
        System.out.print(MIN);
    }

    private static void dfs(char[][] matrix, List<Cctv> cctvs, int depth) {
        if (depth == cctvs.size()) { // 모든 cctv를 다 설치한 상태
            MIN = Math.min(MIN, countZero(matrix));
            return;
        }

        Cctv cctv = cctvs.get(depth);
        char[][] nextMatrix;

        switch (cctv.value) {
            case '1': // 4방향을 돌아야 함 그 때 마다 dfs를 호출
                for (int i = 0; i < 4; i++) {
                    char[][] tmp = cloneMatrix(matrix);

                    if (i == 0) { // 위 쪽으로 모두 채우기
                        nextMatrix = fillUp(tmp, cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 1) {
                        nextMatrix = fillRight(tmp, cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 2) {
                        nextMatrix = fillDown(tmp, cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 3) {
                        nextMatrix = fillLeft(tmp, cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                }
                break;
            case '2':
                for (int i = 0; i < 2; i++) {
                    char[][] tmp = cloneMatrix(matrix);

                    if (i == 0) { // 세로 방향
                        nextMatrix = fillDown(fillUp(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 1) {
                        nextMatrix = fillLeft(fillRight(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                }
                break;
            case '3':
                for (int i = 0; i < 4; i++) {
                    char[][] tmp = cloneMatrix(matrix);

                    if (i == 0) {
                        nextMatrix = fillUp(fillRight(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 1) {
                        nextMatrix = fillRight(fillDown(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 2) {
                        nextMatrix = fillDown(fillLeft(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 3) {
                        nextMatrix = fillLeft(fillUp(tmp, cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                }
                break;
            case '4':
                for (int i = 0; i < 4; i++) {
                    char[][] tmp = cloneMatrix(matrix);

                    if (i == 0) {
                        nextMatrix = fillLeft(fillUp(fillRight(tmp, cctv.point), cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 1) {
                        nextMatrix = fillUp(fillRight(fillDown(tmp, cctv.point), cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 2) {
                        nextMatrix = fillRight(fillDown(fillLeft(tmp, cctv.point), cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                    if (i == 3) {
                        nextMatrix = fillDown(fillLeft(fillUp(tmp, cctv.point), cctv.point), cctv.point);
                        dfs(nextMatrix, cctvs, depth + 1);
                    }
                }
                break;
            case '5':
                char[][] tmp = cloneMatrix(matrix);
                nextMatrix = fillUp(fillRight(fillDown(fillLeft(tmp, cctv.point), cctv.point), cctv.point), cctv.point);
                dfs(nextMatrix, cctvs, depth + 1);
                break;
        }
    }

    private static char[][] fillUp(char[][] matrix, Point point) {
        int nextIdx = point.x - 1;
        while (nextIdx >= 0 && matrix[nextIdx][point.y] != '6') {
            if (matrix[nextIdx][point.y] == '0') { // 빈 공간
                matrix[nextIdx][point.y] = '#';
            }
            nextIdx--;
        }

        return matrix;
    }

    private static char[][] fillRight(char[][] matrix, Point point) {
        int nextIdx = point.y + 1;
        while (nextIdx < M && matrix[point.x][nextIdx] != '6') {
            if (matrix[point.x][nextIdx] == '0') {
                matrix[point.x][nextIdx] = '#';
            }
            nextIdx++;
        }
        return matrix;
    }

    private static char[][] fillDown(char[][] matrix, Point point) {
        int nextIdx = point.x + 1;
        while (nextIdx < N && matrix[nextIdx][point.y] != '6') {
            if (matrix[nextIdx][point.y] == '0') {
                matrix[nextIdx][point.y] = '#';
            }
            nextIdx++;
        }
        return matrix;
    }

    private static char[][] fillLeft(char[][] matrix, Point point) {
        int nextIdx = point.y - 1;
        while (nextIdx >= 0 && matrix[point.x][nextIdx] != '6') {
            if (matrix[point.x][nextIdx] == '0') {
                matrix[point.x][nextIdx] = '#';
            }
            nextIdx--;
        }
        return matrix;
    }

    private static char[][] cloneMatrix(char[][] matrix) {
        char[][] newMatrix = new char[N][M];
        for (int i = 0; i < N; i++) {
            newMatrix[i] = matrix[i].clone();
        }
        return newMatrix;
    }

    private static int countZero(char[][] matrix) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == '0') {
                    count++;
                }
            }
        }
        return count;
    }

}
