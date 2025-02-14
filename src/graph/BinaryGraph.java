package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BinaryGraph {
    static List<List<Integer>> graph;
    static int []color;
    static boolean isBinaryGraph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            isBinaryGraph = true;
            graph = new ArrayList<>();
            color = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0 && isBinaryGraph) {
                    bfs(i);
                }
            }

            sb.append(isBinaryGraph ? "YES" : "NO").append("\n");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int next : graph.get(node)) {
                if (color[next] == 0) {
                    color[next] = -color[node];
                    q.add(next);
                }
                else if (color[next] == color[node]) {
                    isBinaryGraph = false;
                    return;
                }
            }
        }
    }

}
