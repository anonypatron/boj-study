import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 17472
    /*
        1. 섬 라벨링(1부터 n까지)
        2. 각각의 섬 가장자리에서부터 다른섬의 가장 가까운 좌표에 대해 좌표와 거리를 계산한다. (단, 길이는 2보다 크고 가장 짧은 길이를 하나 찾는다.)
            ex) 1번 섬과 2번 섬 사이의 최단거리(2이상), 1번 섬과 3번 섬 사이의 최단거리(3이상), ...
        3. 2에서 구한 Edge를 가지고 mst를 만든다.
     */
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
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
    static List<Edge> edges; // 간선들
    static boolean[][] visited;
    static int[][] origin;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parents, rank;
    static int N, M, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        visited = new boolean[N][M];
        edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandCnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (origin[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, islandCnt++);
                }
            }
        }
        V = islandCnt - 1; // 섬의 개수

        getEdges();
        Collections.sort(edges);
        System.out.print(kruskal());
    }

    // mst
    private static int kruskal() {
        parents = new int[V + 1];
        rank = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        int result = 0, cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.weight;
                cnt++;
                if (cnt == V - 1) {
                    return result;
                }
            }
        }
        return -1;
    }

    private static void getEdges() {
        int[][] dist = new int[V + 1][V + 1];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) { // 모든 섬들의 (i는 섬번호)
            for (int j = 0; j < M; j++) { // 각각의 포인트에 대해
                if (origin[i][j] != 0) { // 섬이고
                    boolean isEdge = false;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (checkRange(nx, ny) && origin[nx][ny] == 0) {
                            isEdge = true;
                            break;
                        }
                    }

                    if (!isEdge) continue;

                    // 가장자리라면
                    for (int k = 0; k < 4; k++) { // 4방향으로
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        int length = 0;

                        while (checkRange(nx, ny)) {
                            int from = origin[i][j];
                            int to = origin[nx][ny];

                            if (to == 0) { // 바다면
                                nx += dx[k];
                                ny += dy[k];
                                length++;
                            }
                            else { // 섬을 만난 경우
                                if (from != to && length > 1 && dist[from][to] > length) { // 다른 섬이고
                                    dist[from][to] = length;
                                    dist[to][from] = length;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            for (int j = i + 1; j < dist.length; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    edges.add(new Edge(i, j, dist[i][j]));
                }
            }
        }
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parents[rootX] = rootY;
            rank[rootY] += rank[rootX];
        }
        else {
            parents[rootY] = rootX;
            rank[rootX] += rank[rootY];
        }
        return true;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void bfs(int r, int c, int idx) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(r, c));
        visited[r][c] = true;
        origin[r][c] = idx;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (checkRange(nx, ny) && !visited[nx][ny] && origin[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    origin[nx][ny] = idx;
                }
            }
        }
    }

    private static boolean checkRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
