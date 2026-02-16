import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 1647
    static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
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

        edges = new ArrayList<>();
        parents = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, w));
        }

        Collections.sort(edges);

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        int sum = 0, cnt = 0, maxValue = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                sum += e.weight;
                cnt++;
                maxValue = Math.max(maxValue, e.weight);

                if (cnt == N - 1) {
                    break;
                }
            }
        }

        System.out.print(sum - maxValue);
    }

    private static boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) {
            return false;
        }
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
