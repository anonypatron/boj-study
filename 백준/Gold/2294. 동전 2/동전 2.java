import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 2294
    /*
        x를 만들기 위해서 필요한 동전의 개수 -> dp[x]
        ex) 코인이 1, 5, 12가 있고 15를 만들기 위해서 필요한 동전의 개수
        -> dp[15] = dp[15 - 1] + 1
        -> dp[15] = dp[15 - 5] + 1
        -> dp[15] = dp[15 - 12] + 1
        셋 중 가장 작은거
     */
    static final int INF = 10_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];
        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= k; i++) {
            dp[i] = INF;
        }

        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int coin : coins) { // 각각의 동전에 대해서
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[k] == INF) {
            System.out.print(-1);
        }
        else {
            System.out.print(dp[k]);
        }
    }

}
