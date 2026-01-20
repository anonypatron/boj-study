import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 7453
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        long[] listAB = new long[n * n];
        long[] listCD = new long[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                listAB[idx] = (A[i] + B[j]);
                listCD[idx] = (C[i] + D[j]);
                idx++;
            }
        }

        Arrays.sort(listAB);
        Arrays.sort(listCD);

        int listSize = listAB.length;
        int left = 0, right = listSize - 1;
        long result = 0;
        while (left < listSize && right >= 0) {
            long a = listAB[left];
            long b = listCD[right];
            long sum = a + b;
            int cntAB = 0, cntCD = 0;

            if (sum == 0) {
                while (left < listSize && listAB[left] == a) {
                    left++;
                    cntAB++;
                }
                while (right >= 0 && listCD[right] == b) {
                    right--;
                    cntCD++;
                }

                result += (long) cntAB * cntCD;
            }
            else if (sum < 0) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.print(result);
    }

}
