package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MakeZero2 {
    static List<String> list;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            recur(new StringBuilder(), 1);

            Collections.sort(list);
            for (String s : list) {
                sb.append(s).append('\n');
            }
            sb.append('\n');
        }

        System.out.print(sb.delete(sb.length() - 2, sb.length()));
    }

    private static void recur(StringBuilder cur, int number) {
        cur.append(number);

        if (cur.length() == 2 * N - 1) {
            if (isPossible(cur.toString())) {
                list.add(cur.toString());
            }
            return;
        }

        cur.append('+');
        recur(cur, number + 1);
        cur.delete(cur.length() - 2, cur.length());

        cur.append('-');
        recur(cur, number + 1);
        cur.delete(cur.length() - 2, cur.length());

        cur.append(' ');
        recur(cur, number + 1);
        cur.delete(cur.length() - 2, cur.length());
    }

    private static boolean isPossible(String str) {
        Deque<Integer> numberQ = new ArrayDeque<>();
        Deque<Character> charQ = new ArrayDeque<>();
        StringBuilder numberSb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '+' || c == '-') {
                numberQ.addLast(Integer.parseInt(numberSb.toString()));
                numberSb.setLength(0);
                charQ.addLast(c);

                if (numberQ.size() > 1 && !charQ.isEmpty()) {
                    char command = charQ.pollFirst();

                    if (command == '+') {
                        numberQ.addLast(numberQ.removeFirst() + numberQ.removeFirst());
                    }
                    else if (command == '-'){
                        numberQ.addLast(numberQ.removeFirst() - numberQ.removeFirst());
                    }
                }
            }
            else if (c != ' ') {
                numberSb.append(c);
            }
        }

        numberQ.addLast(Integer.parseInt(numberSb.toString()));

        if (numberQ.size() > 1 && !charQ.isEmpty()) {
            char command = charQ.pollFirst();

            if (command == '+') {
                numberQ.addLast(numberQ.removeFirst() + numberQ.removeFirst());
            }
            else if (command == '-'){
                numberQ.addLast(numberQ.removeFirst() - numberQ.removeFirst());
            }
        }

        return numberQ.removeFirst() == 0;
    }

}
