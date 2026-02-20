import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1774
    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;
        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Long.compare(weight, o.weight);
        }
    }
    static class Info {
        int number, x, y;
        public Info(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
    static List<Edge> edges;
    static int[] parents, rank;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Info[] arr = new Info[N + 1];
        edges = new ArrayList<>();
        parents = new int[N + 1];
        rank = new int[N + 1];

        // 새로 이어야할 통로 정보
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Info(i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            parents[i] = i;
            rank[i] = 1;
        }

        int connectCnt = 0;
        // 기존에 연결된 통로
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (union(from, to)) {
                connectCnt++;
            }
        }

        addEdges(arr);
        System.out.printf("%.2f", kruskal(connectCnt));
    }

    private static double kruskal(int need) {
        Collections.sort(edges);
        double result = 0D;
        int cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += Math.sqrt(e.weight);
                if (++cnt == N - need - 1) {
                    return result;
                }
            }
        }
        return -1;
    }

    private static void addEdges(Info[] arr) {
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                Info cur = arr[i];
                Info next = arr[j];
                int dx = Math.abs(cur.x - next.x);
                int dy = Math.abs(cur.y - next.y);
                long weight = ((long) dx * dx + (long) dy * dy);

                edges.add(new Edge(cur.number, next.number, weight));
            }
        }
    }

    private static boolean union(int x, int y) {
        int a = find(x);
        int b = find(y);

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

}
