import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main { // 3649
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            int target = Integer.parseInt(s) * 10_000_000;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int left = 0, right = n - 1;
            boolean isPossible = false;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == target) {
                    isPossible = true;
                    break;
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }

            if (isPossible) {
                sb.append("yes ").append(arr[left]).append(' ').append(arr[right]);
            }
            else { // 끝까지 갔는데 못찾으면
                sb.append("danger");
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

}
