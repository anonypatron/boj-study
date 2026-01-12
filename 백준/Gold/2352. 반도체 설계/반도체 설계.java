import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];

        lis[0] = arr[1];
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            int x = arr[i];

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

        System.out.print(idx + 1);
    }

}
