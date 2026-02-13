import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 1915
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int max = 0;
        int[][] origin = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                origin[i][j] = str.charAt(j) - '0';
            }
        }
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = origin[i][0];
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = origin[0][i];
            max = Math.max(max, dp[0][i]);
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (origin[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        System.out.print(max * max);
    }

}
