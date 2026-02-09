import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 11049
    /*
        dp[i][j] -> i부터 j까지의 곱셈 연산 횟수의 최솟값
        dp[i][j] = dp[i][k] + dp[k + 1][j] + (matrices[i][0] * matrices[k][1] * matrices[j][1])
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] matrices = new int[n + 1][2];
        long[][] dp = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrices[i][0] = r;
            matrices[i][1] = c;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i <= n; i++) { // 행렬이 하나면 0
            dp[i][i] = 0;
        }

        for (int i = 1; i < n; i++) { // 길이가 2일 때
            dp[i][i + 1] = (long) matrices[i][0] * matrices[i][1] * matrices[i + 1][1];
        }

        for (int len = 3; len <= n; len++) { // 길이(3부터)
            for (int i = 1; i <= n - len + 1; i++) { // 시작점
                int j = len + i - 1;
                for (int k = i; k < j; k++) { // 어디까지 자를건지? (i < k < j)
                    // dp[i][j] = dp[i][k] + dp[k + 1][j] + (앞의 두 덩어리를 곱섭하는 연산의 수)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + ((long) matrices[i][0] * matrices[k][1] * matrices[j][1]));
                }
            }
        }

        System.out.print(dp[1][n]);
    }

}
