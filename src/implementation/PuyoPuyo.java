package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PuyoPuyo { // 11559
    static final int N = 12, M = 6;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    static char[][] matrix = new char[N][M];
    static boolean[][] visited = new boolean[N][M];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < matrix.length; i++) {
            String line = br.readLine();
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int result = 0;
        while (act()) {
            dropDot();
            result++;
        }

        System.out.print(result);
    }

    private static void dropDot() {
        for (int i = 0; i < M; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (matrix[j][i] != '.') {
                    list.add(matrix[j][i]);
                }
            }

            for (int j = 0; j < N - list.size(); j++) {
                matrix[j][i] = '.';
            }

            int idx = 0;
            for (int j = N - list.size(); j < N; j++) {
                matrix[j][i] = list.get(idx++);
            }
        }
    }

    // 인접한 곳에 4개 이상 있는지 확인하기 있으면 제거함
    private static boolean act() {
        visited = new boolean[N][M];
        boolean flag = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != '.' && !visited[i][j]) {
                    if (bfs(i, j)) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean bfs(int x, int y) {
        List<Point> targets = new ArrayList<>();
        Deque<Point> q = new ArrayDeque<>();
        q.addLast(new Point(x, y));
        targets.add(new Point(x, y));
        visited[x][y] = true;

        int count = 1;
        while (!q.isEmpty()) {
            Point p = q.pollLast();

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if (checkRange(newX, newY) && !visited[newX][newY]) {
                    if (matrix[p.x][p.y] == matrix[newX][newY]) {
                        q.addLast(new Point(newX, newY));
                        targets.add(new Point(newX, newY));
                        visited[newX][newY] = true;
                        count++;
                    }
                }
            }
        }

        if (count > 3) {
            remove(targets);
            return true;
        }
        return false;
    }

    private static void remove(List<Point> targets) {
        for (Point p : targets) {
            matrix[p.x][p.y] = '.';
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && matrix[x][y] != '.';
    }

}
