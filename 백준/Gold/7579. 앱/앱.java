import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 7579
    /*
        dp[비용] = 최대 메모리 바이트 수
     */
    static int[] memories, values, dp;
    static int N, M, MAX_COST;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAX_COST = N * 100;
        dp = new int[MAX_COST + 1];
        memories = new int[N + 1];
        values = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) { // 각각의 앱에 대해서
            for (int j = MAX_COST; j >= values[i]; j--) { // 뒤에서 부터 돌려야 중복으로 끄지 않음
                dp[j] = Math.max(dp[j], dp[j - values[i]] + memories[i]);
            }
        }

        for (int i = 0; i <= MAX_COST; i++) {
            if (dp[i] >= M) {
                System.out.print(i);
                break;
            }
        }
    }

}
