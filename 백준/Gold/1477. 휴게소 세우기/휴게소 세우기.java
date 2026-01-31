import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 1477
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 2];

        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n + 1] = l;
        Arrays.sort(arr);

        // 거리 구하기
        int[] distance = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distance[i] = arr[i + 1] - arr[i];
        }
        Arrays.sort(distance);

        int left = 1, right = distance[n], result = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = 0;

            for (int i = 0; i < n + 1; i++) { // 각각의 거리에 대해 총 몇 개를 설치해야 하는지
                count += (distance[i] - 1) / mid;
            }

            if (count <= m) { // m개 이하로 설치가 가능하면
                result = Math.min(result, mid);
                right = mid - 1;
            }
            else { // 불가능하면
                left = mid + 1;
            }
        }

        System.out.print(result);
    }

}
