package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AtoB2 {
    static String S, T;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.print(answer);
    }

    public static void dfs(String t) {
        if (t.length() == S.length()) {
            if (S.equals(t)) {
                answer = 1;
            }
            return;
        }

        if (t.charAt(t.length() - 1) == 'A') {
            dfs(t.substring(0, t.length() - 1));
        }

        if (t.charAt(0) == 'B') {
            StringBuilder tmp = new StringBuilder(t);
            dfs(tmp.reverse().deleteCharAt(tmp.length() - 1).toString());
        }

    }

}
