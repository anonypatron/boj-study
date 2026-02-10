import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 11066
    /*
        dp[i][j] -> i부터 j까지 합치는데 필요한 최소 비용 (길이는 2로 고정하기)
        dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j] - prefixSum[i - 1]);
        ex) 40 30 30 50
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];
            int[] prefixSum = new int[n + 1];
            int[][] dp = new int[n + 1][n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            for (int len = 1; len <= n; len++) { // 슬라이딩 윈도우
                sum += arr[len];
                prefixSum[len] = sum;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            // 크누스 최적화를 통해 n^3을 n^2으로 줄일 수 있음 -> 백준 13974번 참조
            for (int len = 2; len <= n; len++) {
                for (int i = 1; i <= n - len + 1; i++) { // 시작 지점
                    int j = i + len - 1;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (prefixSum[j] - prefixSum[i - 1]));
                    }
                }
            }

            sb.append(dp[1][n]).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
