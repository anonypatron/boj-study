package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Queues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    q.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.isEmpty() ? -1 : q.poll()).append("\n");;
                    break;
                case "size":
                    sb.append(q.size()).append("\n");;
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? 1 : 0).append("\n");;
                    break;
                case "front":
                    sb.append(q.isEmpty() ? -1 : q.peek()).append("\n");;
                    break;
                case "back":
                    sb.append(q.isEmpty() ? -1 : q.getLast()).append("\n");
                    break;
            }

        }

        System.out.println(sb);
    }
}
