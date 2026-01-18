import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int left = 0, right = 0; // left는 가장 큰 값, right는 모두를 더 한 값

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            arr[i] = x;
            left = Math.max(left, x);
            right += x;
        }

        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2; // 블루레이의 크기
            int sum = 0, count = 1;
            
            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) { // 현재 크기에 못 담으면 다음으로 넘어가기
                    count++;
                    sum = arr[i];
                }
                else {
                    sum += arr[i];
                }
            }

            if (count > m) { // 현재 크기보다 더 커야함.
                left = mid + 1;
            }
            else {
                result = mid;
                right = mid - 1;
            }
        }

        System.out.print(result);
    }

}
