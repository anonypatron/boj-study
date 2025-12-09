package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class QueueStack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Deque<Integer> dq = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (arr[i] == 0) {
                dq.addFirst(x);
            }
        }

        int m = Integer.parseInt(br.readLine());
        st =  new StringTokenizer(br.readLine());

        while (m-- > 0) {
            int x = Integer.parseInt(st.nextToken());
            dq.addLast(x);
            sb.append(dq.pollFirst()).append(' ');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
