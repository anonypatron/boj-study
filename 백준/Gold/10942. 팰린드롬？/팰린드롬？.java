import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 10942
    /*
        2차원 dp배열 만들어서 채우기
        ex) dp[i][j] -> i부터 j까지가 팰린드롬인가? 맞으면 1, 아니면 0
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        boolean[][] dp = new boolean[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 길이가 1일 때
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }

        // 길이가 2일 때
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 길이가 3이상일 때
        for (int len = 3; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (arr[i] == arr[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (dp[start][end]) {
                sb.append(1);
            }
            else {
                sb.append(0);
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
