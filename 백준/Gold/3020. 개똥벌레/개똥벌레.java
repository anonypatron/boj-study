import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 3020
    static int min = Integer.MAX_VALUE;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] top = new int [n / 2];
        int[] bottom = new int [n / 2];
        boolean isTop = false;
        int idx = 0;

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (isTop) {
                top[idx++] = x;
                isTop = false;
            }
            else {
                bottom[idx] = x;
                isTop = true;
            }
        }

        Arrays.sort(top);
        Arrays.sort(bottom);

        // 각각의 높이에 대해서 충돌하는 종유석/석순의 개수를 세기
        for (int i = 1; i <= h; i++) {
            int bottomCnt = bottom.length - getConflictSize(bottom, i);
            int topCnt = top.length - getConflictSize(top, h - i + 1);

            int sum = bottomCnt + topCnt;

            if (sum < min) {
                min = sum;
                count = 1;
            }
            else if (sum == min) {
                count++;
            }
        }

        System.out.printf("%d %d", min, count);
    }

    // lower bound 찾기
    private static int getConflictSize(int[] arr, int height) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < height) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }

}
