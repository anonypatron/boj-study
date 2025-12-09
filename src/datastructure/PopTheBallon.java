package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class PopTheBallon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Deque<int []> dq = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        int index = 1;

        st = new StringTokenizer(br.readLine());
        while (n-- > 0) {
            dq.addLast(new int[] { Integer.parseInt(st.nextToken()), index++ });
        }

        while (!dq.isEmpty()) {
            int []tmp = dq.pollFirst();
            sb.append(tmp[1]).append(' ');

            int x = tmp[0];
            if (dq.isEmpty()) {
                break;
            }
            if (x > 0) {
                while (x-- > 1) {
                    dq.addLast(dq.pollFirst());
                }
            }
            else {
                while (x++ < 0) {
                    dq.addFirst(dq.pollLast());
                }
            }
        }

        System.out.print(sb.deleteCharAt(sb.length()-1));
    }

}
