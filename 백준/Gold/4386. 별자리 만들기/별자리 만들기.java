import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 4386(프림 -> 좌표만 주어질 때 크루스칼보다 편하다)
    static double[][] stars;
    static double[] dist;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        stars = new double[N][2];
        dist = new double[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
        double result = 0;
        for (int i = 0; i < N; i++) {
            int cur = -1;
            double min = Double.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    cur = j;
                }
            }

            visited[cur] = true;
            result += min;

            for (int next = 0; next < N; next++) {
                if (!visited[next]) {
                    double dx = stars[next][0] - stars[cur][0];
                    double dy = stars[next][1] - stars[cur][1];
                    double cost = Math.sqrt(dx * dx + dy * dy);

                    if (cost < dist[next]) {
                        dist[next] = cost;
                    }
                }
            }
        }

        System.out.printf("%.2f", result);
    }

}
