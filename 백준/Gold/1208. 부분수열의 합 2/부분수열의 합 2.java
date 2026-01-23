import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 1208
    static List<Integer> leftList = new ArrayList<>();
    static List<Integer> rightList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = Arrays.copyOfRange(arr, 0, n / 2);
        int[] right = Arrays.copyOfRange(arr, n / 2, n);

        backTracking(left, 0, 0, true);
        backTracking(right, 0, 0, false);

        Collections.sort(leftList);
        Collections.sort(rightList);

        int l = 0, r = rightList.size() - 1;
        long result = 0;
        while (l < leftList.size() && r >= 0) {
            int leftValue = leftList.get(l);
            int rightValue = rightList.get(r);
            int sum = leftValue + rightValue;

            if (sum == s) {
                int lCount = 0, rCount = 0;
                while (l < leftList.size() && leftList.get(l) == leftValue) {
                    l++;
                    lCount++;
                }
                while (r >= 0 && rightList.get(r) == rightValue) {
                    r--;
                    rCount++;
                }
                result += (long) lCount * rCount;
            }
            else if (sum > s) {
                r--;
            }
            else {
                l++;
            }
        }

        // 공집합 카운트
        if (s == 0) {
            result--;
        }

        System.out.print(result);
    }

    private static void backTracking(int[] arr, int depth, int sum, boolean isLeft) {
        if (depth == arr.length) {
            if (isLeft) {
                leftList.add(sum);
            }
            else {
                rightList.add(sum);
            }
            return;
        }

        backTracking(arr, depth + 1, sum + arr[depth], isLeft);
        backTracking(arr, depth + 1, sum, isLeft);
    }

}
