import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 1629
    static long c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        System.out.print(pow(a, b));
    }

    private static long pow(long a, long b) {
        // b가 짝수일 때 a^b = a^(b/2) * a^(b/2)
        // b가 홀수일 때 a^b = a^(b/2) * a^(b/2) * a
        if (b == 0) return 1;

        long half = pow(a, b / 2);
        long result = half * half % c;

        if (b % 2 == 0) {
            return result % c;
        }
        else {
            return result * a % c;
        }
    }

}
