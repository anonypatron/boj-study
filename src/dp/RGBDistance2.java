package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistance2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int [][]houses = new int[n][3];
        int [][]dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = houses[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        dp[i][j] = houses[i][j] + Math.min(dp[i - 1][j + 1], dp[i - 1][j + 2]);
                        break;
                    case 1:
                        dp[i][j] = houses[i][j] + Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]);
                        break;
                    case 2:
                        dp[i][j] = houses[i][j] + Math.min(dp[i - 1][j - 1], dp[i - 1][j - 2]);
                        break;
                }
            }
        }

        int answer = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        System.out.print(answer);
    }

}
