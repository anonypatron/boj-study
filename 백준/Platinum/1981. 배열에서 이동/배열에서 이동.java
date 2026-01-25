import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 1981
    /*
        예시로 min값이 0, max값이 8이라고 했을 때 mid = (min + max) / 2 = 4
        1. [0, 4], [1, 5], [2, 6], [3, 7], [4, 8]을 탐색하다가 하나라도 가능하면 mid값은 후보
        2. 가능하다면 반을 갈라서 왼쪽을 탐색
        3. 불가능하면 반을 갈라서 오른쪽을 탐색
     */
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] matrix;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               int x = Integer.parseInt(st.nextToken());
               matrix[i][j] = x;
               min = Math.min(min, x);
               max = Math.max(max, x);
            }
        }

        int left = 0, right = max - min;
        int result = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean isPossible = false;

            for (int lb = min; lb + mid <= max; lb++) {
                visited = new boolean[N][N];

                // lower bound, upper bound 범위 내에서 가능하다면
                if (lb <= matrix[0][0] && matrix[0][0] <= lb + mid) {
                    if (bfs(lb,lb + mid)) {
                        isPossible = true;
                        break;
                    }
                }
            }

            if (isPossible) {
                result = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.print(result);
    }

    private static boolean bfs(int lb, int ub) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny] &&
                        lb <= matrix[nx][ny] && matrix[nx][ny] <= ub) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
