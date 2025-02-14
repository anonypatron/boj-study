package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringGame2 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());
            act(w, k);
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    public static void act(String w, int k) {
        List<List<Integer>> alphabets = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            alphabets.add(new ArrayList<>());
        }

        for (int i = 0; i < w.length(); i++) {
            alphabets.get(w.charAt(i) - 'a').add(i);
        }

        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;

        for (List<Integer> alphabet : alphabets) {
            if (alphabet.size() >= k) {
                List<Integer> indices = alphabet;

                for (int j = 0; j <= indices.size() - k; j++) {
                    int length = indices.get(j + k - 1) - indices.get(j) + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        if (maxLength == Integer.MIN_VALUE) {
            sb.append(-1);
        }
        else {
            sb.append(minLength).append(" ").append(maxLength);
        }
        sb.append("\n");
    }

}
