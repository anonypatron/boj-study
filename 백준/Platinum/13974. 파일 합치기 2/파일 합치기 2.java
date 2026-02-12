import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 13974
    /*
        크누스 최적화
        어떤 구간 [i][j]의 최적 k는 항상 이전 구간의 최적 k 근처에 존재한다.
        opt[i][j - 1] <= opt[i][j] <= opt[i + 1][j]
     */
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];
            int[] prefixSum = new int[n + 1];
            int[][] dp = new int[n + 1][n + 1];
            int[][] opt = new int[n + 1][n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            for (int i = 1; i <= n; i++) {
                opt[i][i] = i;
            }

            // 크누스 최적화
            for (int len = 2; len <= n; len++) {
                for (int i = 1; i <= n - len + 1; i++) { // 시작 지점
                    int j = i + len - 1;
                    int start = opt[i][j - 1];
                    int end = opt[i + 1][j];

                    dp[i][j] = INF;

                    for (int k = start; k <= end && k < j; k++) {
                        int cost = dp[i][k] + dp[k + 1][j] + (prefixSum[j] - prefixSum[i - 1]);
                        if (cost < dp[i][j]) {
                            dp[i][j] = cost;
                            opt[i][j] = k;
                        }
                    }
                }
            }

            sb.append(dp[1][n]).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
