import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 1197
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
    static List<Edge> edges;
    static int[] parents, rank;
    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        parents = new int[V + 1];
        rank = new int[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges);

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        int sum = 0, cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) { // 사이클이 일어나지 않으면
                sum += e.weight;
                cnt++;

                if (cnt == V - 1) {
                    break;
                }
            }
        }

        System.out.print(sum);
    }

    private static boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) {
            return false;
        }

        // 유니온 파인드 최적화
        if (rank[x] < rank[y]) {
            parents[x] = y;
            rank[y] += rank[x];
        }
        else {
            parents[y] = x;
            rank[x] += rank[y];
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
