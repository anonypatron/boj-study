import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // 2133
    /*
        dp[i] -> 3xi 크기의 벽을 타일로 채우는 경우의 수
        n이 홀 수면 0, 짝수면 점화식
        2 -> 3
        4 -> 11(dp[i - 2] * 3 + 2)
        6 -> 33(dp[i - 2] * 3)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n % 2 == 1) {
            System.out.print(0);
            return;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] += dp[i - 2] * 3;
            for (int j = 0; j <= i - 4; j += 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.print(dp[n]);
    }

}
