package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringGameVersion2 { // 20437
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            int[] alphabet = new int[26];
            for (int i = 0; i < w.length(); i++) {
                alphabet[w.charAt(i) - 'a']++;
            }

            // 1번째로 나오는 알파벳과 2번쨰로 나오는 알파벳만 보면 됨
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < w.length(); i++) {
                if (alphabet[w.charAt(i) - 'a'] >= k) {
                    int cnt = 1; // 하나는 이미 찾음

                    for (int j = i + 1; j < w.length(); j++) {
                        if (w.charAt(i) == w.charAt(j)) {
                            cnt++;
                        }
                        if (cnt == k) {
                            min = Math.min(min, j - i + 1);
                            max = Math.max(max, j - i + 1);
                            break;
                        }
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append("-1");
            }
            else {
                sb.append(min).append(' ').append(max);
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
