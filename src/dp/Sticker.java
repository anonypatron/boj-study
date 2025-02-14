package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sticker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int [][]stickers = new int[2][n];
            int [][]dp = new int[2][n];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];

            for (int j = 1; j < n; j++) {
                dp[0][j] = Math.max(dp[1][j - 1] + stickers[0][j], dp[0][j - 1]);
                dp[1][j] = Math.max(dp[0][j - 1] + stickers[1][j], dp[1][j - 1]);
            }

            int max = Math.max(dp[0][n - 1], dp[1][n - 1]);

            sb.append(max).append("\n");
        }

        System.out.print(sb);
    }
}
