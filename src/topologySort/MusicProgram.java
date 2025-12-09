package topologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MusicProgram { // 2623
    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] indegree;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        indegree = new int[n + 1];

        while (m-- > 0) {
            String[] line = br.readLine().split(" ");
            for (int j = 1; j < Integer.parseInt(line[0]); j++) {
                graph.get(Integer.parseInt(line[j])).add(Integer.parseInt(line[j + 1]));
                indegree[Integer.parseInt(line[j + 1])]++;
            }
        }

        topologySort();
        System.out.print(count == n ? sb.deleteCharAt(sb.length() - 1) : 0);
    }

    private static void topologySort() {
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.addLast(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            sb.append(cur).append('\n');
            count++;
            for (int next : graph.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.addLast(next);
                }
            }
        }
    }

}
