import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 6497
    static class Edge implements Comparable<Edge>{
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            edges = new ArrayList<>();
            parents = new int[m];
            rank = new int[m];

            for (int i = 0; i < m; i++) {
                parents[i] = i;
                rank[i] = 1;
            }

            long totalWeight = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, weight));
                totalWeight += weight;
            }
            Collections.sort(edges);

            long result = 0, cnt = 0;
            for (Edge e : edges) {
                if (union(e.from, e.to)) {
                    result += e.weight;
                    if (++cnt == m - 1) {
                        break;
                    }
                }
            }
            sb.append(totalWeight - result).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
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
