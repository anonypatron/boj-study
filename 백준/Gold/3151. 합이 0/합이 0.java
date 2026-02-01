import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 3151
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long count = 0;
        for (int i = 0; i < n - 2; i++) {
            if (arr[i] > 0) break;
            int fixedValue = arr[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                int leftValue = arr[left], rightValue = arr[right];
                int sum = fixedValue + leftValue + rightValue;

                if (sum == 0) {
                    if (leftValue == rightValue) {
                        int len = right - left + 1;
                        count += (long) len * (len - 1) / 2;
                        break;
                    }

                    int lCnt = 0, rCnt = 0;
                    while (left < right && leftValue == arr[left]) { // left가 right를 방해할 수 있음(유의할 것)
                        left++;
                        lCnt++;
                    }
                    while (left <= right && rightValue == arr[right]) { // right는 left가 범위를 넘어올 수 있으니 체크해야 함)
                        right--;
                        rCnt++;
                    }
                    count += (long) lCnt * rCnt;
                }

                else if (sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        System.out.print(count);
    }

}
