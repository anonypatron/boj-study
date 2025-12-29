import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] graph;
    static int[] indegree;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            graph = new boolean[N + 1][N + 1];
            indegree = new int[N + 1];
            int[] rank = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                rank[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    graph[rank[i]][rank[j]] = true;
                    indegree[rank[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                swap(a, b);
            }
            sb.append(topologySort()).append('\n');
        }

        System.out.print(sb);
    }

    private static String topologySort() {
        Queue<Integer> q = new ArrayDeque<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        boolean flag = false;
        int idx = 0;
        int[] order = new int[N];

        while (!q.isEmpty()) {
            if (q.size() > 1) {
                flag = true;
            }

            int cur = q.poll();
            order[idx++] = cur;

            for (int next = 1; next <= N; next++) {
                if (graph[cur][next]) {
                    graph[cur][next] = false;
                    if (--indegree[next] == 0) {
                        q.add(next);
                    }
                }
            }
        }

        if (idx != N) {
            return "IMPOSSIBLE";
        }
        else if (flag) {
            return "?";
        }

        for (int i = 0; i < N; i++) {
            tmp.append(order[i]).append(" ");
        }

        return tmp.toString();
    }

    private static void swap(int a, int b) {
        if (graph[a][b]) { // a가 b보다 앞에 있다면
            indegree[a]++;
            indegree[b]--;
            graph[a][b] = false;
            graph[b][a] = true;
        }
        else {
            indegree[a]--;
            indegree[b]++;
            graph[a][b] = true;
            graph[b][a] = false;
        }
    }

}
