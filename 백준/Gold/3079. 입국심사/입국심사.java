import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 3079
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            arr[i] = x;
            max = Math.max(max, x);
        }

        long left = 0L, right = (long) max * m;
        long result = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sum = 0L;

            for (int i = 0; i < n; i++) {
                sum += mid / arr[i];
                if (sum >= m) break;
            }

            if (sum >= m) {
                result = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.print(result);
    }

}
