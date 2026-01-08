import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] lis = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        lis[0] = Integer.parseInt(st.nextToken());
        int curIdx = 0;

        for (int i = 1; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());

            if (lis[curIdx] < x) {
                lis[++curIdx] = x;
            }
            else {
                int left = 0, right = curIdx;
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

        System.out.print(curIdx + 1);
    }

}
