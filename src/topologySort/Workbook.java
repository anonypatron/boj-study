package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Workbook {
    static List<List<Integer>> graph;
    static List<Integer> result;
    static int []indegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        graph = new ArrayList<>();
        result = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            indegree[v]++;
        }

        topologySort();

        for (int i = 1; i < result.size(); i++) {
            sb.append(result.get(i)).append(' ');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void topologySort() {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int x = pq.poll();
            result.add(x);

            for (Integer v : graph.get(x)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    pq.add(v);
                }
            }
        }
    }

}
