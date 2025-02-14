package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeZero {
    static List<String> list;
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            dfs(new StringBuilder(), 1);

            Collections.sort(list);
            for (String str : list) {
                sb.append(str).append("\n");
            }
            sb.append("\n");
        }

        System.out.print(sb.delete(sb.length() - 2, sb.length()));
    }

    public static void dfs(StringBuilder formula, int depth) {
        formula.append(depth);

        if (depth == N) {
            if (isZero(formula.toString())) {
                list.add(formula.toString());
            }
            return;
        }

        dfs(new StringBuilder(formula).append("+"), depth + 1);
        dfs(new StringBuilder(formula).append("-"), depth + 1);
        dfs(new StringBuilder(formula).append(" "), depth + 1);
    }

    public static boolean isZero(String tmp) {
        StringBuilder buf = new StringBuilder();
        int sum = 0;
        char sign = '+';

        for (char c : tmp.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            else if (Character.isDigit(c)) {
                buf.append(c);
            }
            else { // + or -
                if (buf.length() > 0) {
                    int num = Integer.parseInt(buf.toString());
                    if (sign == '+') {
                        sum += num;
                    }
                    else if (sign == '-') {
                        sum -= num;
                    }
                    buf.setLength(0);
                }
                sign = c;
            }
        }

        if (buf.length() > 0) {
            int num = Integer.parseInt(buf.toString());
            if (sign == '+') {
                sum += num;
            }
            else if (sign == '-') {
                sum -= num;
            }
        }

        return sum == 0;
    }

}
