import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    /*
        1. a + b + c = target
            a + b = target - c
        2. a + b 조합들을 set에 넣어놓고 target(가장 큰 값부터) c를 뺸다
        3. 조합을 세는 시간 복잡도: O(n^2), target - c를 완전탐색하기 위함: O(n^2), 전체 시간 복잡도: O(n^2)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                set.add(arr[i] + arr[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            int target = arr[n - i - 1];
            for (int j = 0; j < n; j++) {
                if (set.contains(target - arr[j])) {
                    System.out.print(target);
                    return;
                }
            }
        }

    }

}
