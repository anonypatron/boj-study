package cumulativeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HumanComputerSangho { // 16139
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[s.length()][26];
        arr[0][s.charAt(0) - 'a'] = 1;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            System.arraycopy(arr[i - 1], 0, arr[i], 0, 26);
            arr[i][c - 'a'] += 1;
//            System.out.println(Arrays.toString(arr[i]));
        }

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (p > 0) {
                sb.append(arr[q][c - 'a'] - arr[p - 1][c - 'a']);
            }
            else {
                sb.append(arr[q][c - 'a']);
            }
            sb.append('\n');
        }

        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

}
