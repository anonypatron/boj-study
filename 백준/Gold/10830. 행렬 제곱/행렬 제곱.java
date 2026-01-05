import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 10830
    static long[][] matrix;
    static int N;
    static final long DIVIDE = 1000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        matrix = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken()) % DIVIDE;
            }
        }

        long[][] answer = pow(b);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static long[][] pow(long b) {
        if (b == 1) {
            return matrix;
        }

        long[][] half = pow(b / 2);
        long[][] result = dotProduct(half, half);

        if (b % 2 == 0) {
            return result;
        }
        else {
            return dotProduct(result, matrix);
        }
    }

    private static long[][] dotProduct(long[][] matrixA, long[][] matrixB) {
        long[][] tmp = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    tmp[i][j] += (matrixA[i][k] * matrixB[k][j]) % DIVIDE;
                    tmp[i][j] %= DIVIDE;
                }
            }
        }
        return tmp;
    }

}
