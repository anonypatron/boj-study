import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 2230
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int leftIdx = 0, rightIdx = 0;
        int result = Integer.MAX_VALUE;
        while (rightIdx < n) {
            int diff = arr[rightIdx] - arr[leftIdx];
            if (diff >= m) {
                result = Math.min(result, diff);
                if (diff == m) {
                    break;
                }
                leftIdx++;
            }
            else {
                rightIdx++;
            }
        }

        System.out.print(result);
    }

}
