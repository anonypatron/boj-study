import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 2225
    /*
        dp[i][j] -> j개를 더해서 i가 되는 경우의 수

        점화식(마지막이 0이 되는 경우의 수 + 마지막이 1이상인 경우의 수)
        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];

        초기값
        dp[i][1] = 1;
        dp[0][j] = 1;
     */
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int j = 1; j <= k; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        System.out.print(dp[n][k]);
    }

}
