import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1753
    static class Edge implements Comparable<Edge>{
        int to, value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
    static List<Edge>[] graph;
    static int[] dist;
    static int MAX_VALUE = 1_000_000_000;
    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == MAX_VALUE) {
                sb.append("INF");
            }
            else {
                sb.append(dist[i]);
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void dijkstra(int start) {
        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to, curDist = cur.value;

            // 이미 더 짧은 거리로 방문한 적 있으면 다음으로
            if (curDist > dist[curNode]) continue;

            for (Edge next : graph[curNode]) {
                int nextNode = next.to;
                int nextValue = next.value;

                // 현재 정점을 거쳐서 다음 정점으로 가는 거리
                int newDist = dist[curNode] + nextValue;
                
                if (dist[nextNode] > newDist) {
                    dist[nextNode] = newDist;
                    pq.add(new Edge(nextNode, newDist));
                }
            }
        }
    }

}
