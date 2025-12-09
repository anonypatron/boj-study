package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallAndMove {
    static boolean [][][]visited;
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][2];
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.print(bfs());
    }

    private static int bfs() {
        Queue<int []> q = new LinkedList<>();
        visited[0][0][0] = true;
        q.add(new int []{0, 0, 1, 0});

        while (!q.isEmpty()) {
            int []p = q.poll();
            int x = p[0], y = p[1], count = p[2], destroyed = p[3];
            if (x == N - 1 && y == M - 1) return count;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                /*
                    1. 벽일 때와 벽이 아닐 때를 나누너서 계산
                        2. 벽을 부쉈을 때와 부수지 않았을 때를 나누어서 돌리기
                 */
                if (checkRange(nx, ny)) { // 범위 안에 들어옴
                    if (!visited[nx][ny][destroyed] && matrix[nx][ny] == 0) { // 벽이 아님
                        visited[nx][ny][destroyed] = true;
                        q.add(new int []{nx, ny, count + 1, destroyed});
                    }
                    else if (!visited[nx][ny][1] && matrix[nx][ny] == 1 && destroyed == 0) { // 벽임
                        q.add(new int []{nx, ny, count + 1, 1});
                        visited[nx][ny][1] = true;
                    }
                } // end if
            } // end for
        } // end while
        return -1;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
