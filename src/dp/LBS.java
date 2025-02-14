package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LBS {
    static int []increaseDp, decreaseDp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []arr = new int[n];
        increaseDp = new int[n];
        decreaseDp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            increaseDp[i] = 1;
            decreaseDp[i] = 1;
        }

        lis(arr);
        lds(arr);

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, increaseDp[i] + decreaseDp[i]);
        }

        System.out.print(max - 1);
    }

    public static void lis(int []arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    increaseDp[i] = Math.max(increaseDp[i], increaseDp[j] + 1);
                }
            }
        }
    }

    public static void lds(int []arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    decreaseDp[i] = Math.max(decreaseDp[i], decreaseDp[j] + 1);
                }
            }
        }
    }

}
