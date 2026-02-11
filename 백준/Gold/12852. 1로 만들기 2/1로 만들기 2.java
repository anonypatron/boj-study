import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // 12852
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }
        sb.append(dp[n]).append('\n');

        while (n > 0) {
            sb.append(n).append(' ');
            if (n % 3 == 0 && dp[n / 3] == dp[n] - 1) {
                n /= 3;
            }
            else if (n % 2 == 0 && dp[n / 2] == dp[n] - 1) {
                n /= 2;
            }
            else {
                n -= 1;
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
