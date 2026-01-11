import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 2565
    /*
        정렬 + 이분탐색으로 nlogn시간에 풀 수 있는 방법(12015 참고)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        int[] lis = new int[n];

        lis[0] = arr[0][1];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            int x = arr[i][1];

            if (lis[idx] < x) {
                lis[++idx] = x;
            }
            else {
                int left = 0, right = idx;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (lis[mid] < x) {
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                lis[left] = x;
            }
        }

        System.out.print(n - idx - 1);
    }

}
