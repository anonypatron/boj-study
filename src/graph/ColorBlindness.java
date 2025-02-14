package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ColorBlindness {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean [][]visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char [][]matrixA, matrixB;


        matrixA = new char[N][N];
        matrixB = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                matrixA[i][j] = str.charAt(j);
                matrixB[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrixB[i][j] == 'G') {
                    matrixB[i][j] = 'R';
                }
            }
        }

        int result1 = 0, result2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(matrixA, i, j);
                    result1++;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(matrixB, i, j);
                    result2++;
                }
            }
        }

        System.out.print(result1 + " " + result2);
    }

    public static void bfs(char [][]matrix, int x, int y) {
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny] && matrix[nx][ny] == matrix[x][y]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

}
