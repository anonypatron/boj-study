import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main { //2143
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] arrA = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] arrB = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arrA[j];
                listA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += arrB[j];
                listB.add(sum);
            }
        }

        Collections.sort(listA);
        Collections.sort(listB);

        int left = 0, right = listB.size() - 1;
        long result = 0;

        while (left < listA.size() && right >= 0) {
            int a = listA.get(left);
            int b = listB.get(right);
            long sum = a + b;

            if (sum == t) {
                int countA = 0, countB = 0;

                while (left < listA.size() && listA.get(left) == a) {
                    countA++;
                    left++;
                }
                while (right >= 0 && listB.get(right) == b) {
                    countB++;
                    right--;
                }

                result += (long) countA * countB;
            }
            else if (sum > t) {
                right--;
            }
            else {
                left++;
            }
        }

        System.out.print(result);
    }

}
