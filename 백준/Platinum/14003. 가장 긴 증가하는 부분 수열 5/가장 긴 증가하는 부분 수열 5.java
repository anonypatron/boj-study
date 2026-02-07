import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 14003
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n]; // 길이가 len + 1인 LIS의 마지막 값
        int[] pos = new int[n]; // lis의 마지막 값이 원본 배열의 몇 번째 인덱스인지
        int[] prev = new int[n]; // 원본 배열이 lis에 들어갈 때 바로 이전 원소의 인덱스(나중에 거꾸로 추적하기 위함)

        int len = 1;

        Arrays.fill(prev, -1); // 연결 x

        lis[0] = arr[0];
        pos[0] = 0;

        for (int i = 1; i < n; i++) {
            int x = arr[i];

            if (lis[len - 1] < x) {
                lis[len] = x;
                pos[len] = i;
                prev[i] = pos[len++ - 1];
            }
            else {
                int lb = 0, ub = len - 1;
                while (lb <= ub) {
                    int mid = lb + ((ub - lb) >>> 1);

                    if (lis[mid] < x) {
                        lb = mid + 1;
                    }
                    else {
                        ub = mid - 1;
                    }
                }

                lis[lb] = x;
                pos[lb] = i;

                if (lb > 0) {
                    prev[i] = pos[lb - 1];
                }
                else {
                    prev[i] = -1;
                }
            }
        }

        sb.append(len).append('\n');

        /*
            prev를 따라가며 역추적
            ex) cur -> prev[cur] -> prev[prev[cur]] -> ...
         */
        int[] answer = new int[len];
        int cur = pos[len - 1];

        for (int i = len - 1; i >= 0; i--) {
            answer[i] = arr[cur];
            cur = prev[cur];
        }

        for (int i = 0; i < len; i++) {
            sb.append(answer[i]).append(' ');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
