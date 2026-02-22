import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1944
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Edge> edges;
    static Point[] points;
    static int[] parents, rank;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[][] origin;
    static int N, M, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = M + 1;

        edges = new ArrayList<>();
        origin = new char[N][N];
        points = new Point[V];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                origin[i][j] = str.charAt(j);
                if (origin[i][j] == 'S' || origin[i][j] == 'K') {
                    points[idx++] = new Point(i, j);
                }
            }
        }

        for (int i = 0; i < V; i++) {
            bfs(i);
        }
        System.out.print(kruskal());
    }

    private static int kruskal() {
        parents = new int[V];
        rank = new int[V];

        for (int i = 0; i < V; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        Collections.sort(edges);
        int cnt = 0, sum = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                sum += e.weight;
                if (++cnt == V - 1) {
                    return sum;
                }
            }
        }
        return -1;
    }

    private static boolean union(int x, int y) {
        int a = find(x), b = find(y);
        if (a == b) {
            return false;
        }
        if (rank[a] < rank[b]) {
            parents[a] = b;
            rank[b] += rank[a];
        }
        else {
            parents[b] = a;
            rank[a] += rank[b];
        }
        return true;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void bfs(int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        Point start = points[idx];
        q.add(new int[]{start.x, start.y, 0});
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < points.length; i++) {
                if (i != idx && cur[0] == points[i].x && cur[1] == points[i].y) {
                    edges.add(new Edge(idx, i, cur[2]));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny] && origin[nx][ny] != '1') {
                    q.add(new int[]{nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x > 0 && x < N - 1 && y > 0 && y < N - 1;
    }

}
