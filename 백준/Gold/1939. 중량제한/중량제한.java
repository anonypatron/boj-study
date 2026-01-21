import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1939
    /*
        1. 양방향 그래프 만들기(List<List<Object>>)
        2. 최솟값과 최댓값 구하기
        3. 가운데 값을 중량으로 삼고 bfs나 dfs로 도착할 수 있는지 확인하기
        4. 도착할 수 있으면 용량을 늘리기(left = mid + 1)
        5. 도착이 안되면 용량을 줄이기(right = mid - 1)
     */
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static List<List<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        // 그래프 완성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(x).add(new Edge(y, w));
            graph.get(y).add(new Edge(x, w));

            left = Math.min(left, w);
            right = Math.max(right, w);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int result = 1;

        while (left <= right) {
            int weight = left + (right - left) / 2; // 가운데로 중량 잡기
            visited = new boolean[n + 1];

            // mid값으로(중량으로) 갈 수 있는지 확인하기
            if (bfs(start, end, weight)) {
                left = weight + 1;
                result = weight;
            }
            else {
                right = weight - 1;
            }
        }

        System.out.print(result);
    }

    private static boolean bfs(int start, int end, int weight) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == end) {
                return true;
            }

            for (Edge e : graph.get(cur)) {
                if (!visited[e.to] && e.weight >= weight) { // 버틸 수 있는 무게면
                    q.add(e.to);
                    visited[e.to] = true;
                }
            }
        }

        return false;
    }

}
