package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SayMiddle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();

        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            maxQ.add(x);

            if (maxQ.size() > minQ.size() + 1) {
                minQ.add(maxQ.poll());
            }

            while (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
                minQ.add(maxQ.poll());
                maxQ.add(minQ.poll());
            }
            sb.append(maxQ.peek()).append("\n");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
