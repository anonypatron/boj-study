package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LineUp { // 2252
    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> graph;
    static int[] indegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            indegree[b]++;
        }

        topologySort();

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void topologySort() {
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                dq.addLast(i);
            }
        }

        while (!dq.isEmpty()) {
            int x = dq.pollFirst();
            sb.append(x).append(' ');

            for (int next : graph.get(x)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    dq.addLast(next);
                }
            }
        }
    }

}
