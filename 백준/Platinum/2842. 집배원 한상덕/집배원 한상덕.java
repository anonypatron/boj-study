import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 2842
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info {
        char type;
        int height;
        public Info(char type, int height) {
            this.type = type;
            this.height = height;
        }
    }
    static Info[][] matrix;
    static Point start;
    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static boolean[][] visited;
    static int homeCnt = 0;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new Info[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char type = str.charAt(j);
                matrix[i][j] = new Info(type, 0);

                if (type == 'K') {
                    homeCnt++;
                }
                else if (type == 'P') {
                    start = new Point(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = Integer.parseInt(st.nextToken());
                matrix[i][j].height = height;
                set.add(height);
            }
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        int left = 0, right = 0;
        int result = Integer.MAX_VALUE, listSize = list.size();
        while (left <= right && right < listSize) {
            int startHeight = matrix[start.x][start.y].height;
            boolean isPossible = false;

            int lb = list.get(left), ub = list.get(right);
            if (lb <= startHeight && startHeight <= ub) {
                visited = new boolean[N][N];
                if (bfs(lb, ub)) {
                    isPossible = true;
                }
            }

            if (isPossible) {
                result = Math.min(result, ub - lb);
                if (result == 0) {
                    break;
                }
                left++;
            }
            else {
                right++;
            }
        }

        System.out.print(result);
    }

    private static boolean bfs(int lb, int ub) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(start.x, start.y));
        visited[start.x][start.y] = true;

        int count = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (matrix[cur.x][cur.y].type == 'K') {
                count++;
                if (count == homeCnt) {
                    return true;
                }
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny]) {
                    if (lb <= matrix[nx][ny].height && matrix[nx][ny].height <= ub) { // lb와 up 사이에 있다면
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
