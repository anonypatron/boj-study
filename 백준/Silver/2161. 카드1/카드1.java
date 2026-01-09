import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            dq.addLast(i);
        }
        
        while (dq.size() > 1) {
            sb.append(dq.pollFirst()).append(' ');
            dq.addLast(dq.pollFirst());
        }
        
        sb.append(dq.pollFirst());
        
        System.out.print(sb);
    }
}