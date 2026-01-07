import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] answer = new int[3];
        long MIN = Long.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            long fixedValue = arr[i]; // 하나를 잡음
            
            if (fixedValue >= MIN) { // 하나를 잡았는데 min보다 크면 뒤는 볼 필요없음.
                break;
            }
            
            int leftIdx = i + 1, rightIdx = arr.length - 1;

            while (leftIdx < rightIdx) {
                long sum = arr[leftIdx] + arr[rightIdx] + fixedValue;

                if (MIN > Math.abs(sum)) {
                    MIN = Math.abs(sum);
                    answer[0] = arr[i];
                    answer[1] = arr[leftIdx];
                    answer[2] = arr[rightIdx];

                    if (sum == 0) {
                        System.out.printf("%d %d %d", arr[i], arr[leftIdx], arr[rightIdx]);
                        return;
                    }
                }

                if (sum > 0) {
                    rightIdx--;
                }
                else {
                    leftIdx++;
                }
            }
        }

        Arrays.sort(answer);
        System.out.printf("%d %d %d", answer[0], answer[1], answer[2]);
    }

}
