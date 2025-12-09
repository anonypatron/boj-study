package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ACMCraft { // 1005
    static List<List<Integer>> graph;
    static int[] times, indegree, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            times = new int[n + 1];
            indegree = new int[n + 1];
            dp = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                indegree[b]++;
            }
            topologySort();
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void topologySort() {
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i < indegree.length; i++) {
            dp[i] = times[i];
            if (indegree[i] == 0) {
                queue.addLast(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            for (int next : graph.get(cur)) {
                dp[next] = Math.max(dp[next], dp[cur] + times[next]);

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.addLast(next);
                }
            }
        }
    }

}
