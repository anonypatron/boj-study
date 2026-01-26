import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 8983
    /*
        1. 각각의 동물에 대해 사대로 잡을 수 있는지 확인한다.
        2. 사대의 위치를 (x) 동물의 위치를 (a, b)라고 했을 때 거리는 |x - a| + b로 계산한다.
        3. 따라서 |x - a| + b <= L이면 잡히는 것 => a - (L - b) <= x <= a + (L - b) 이 사이에 있으면 잡힘
     */
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] arr;
    static int N, M, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[M];
        Point[] animals = new Point[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isCatch(animals[i])) {
                count++;
            }
        }

        System.out.print(count);
    }

    // 사대안에 이 동물이 있는지?
    private static boolean isCatch(Point p) {
        int a = p.x, b = p.y;
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int distance = Math.abs(arr[mid] - a) + b;

            // 잡을 수 있음
            if (distance <= L) {
                return true;
            }

            // 못잡음
            if (a < arr[mid]) { // 사대보다 왼쪽에 있음
                right = mid - 1;
            }
            else { // 사대보다 오른쪽에 있음
                left = mid + 1;
            }
        }

        return false;
    }

}
