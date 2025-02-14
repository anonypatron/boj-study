package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Library {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x < 0) {
                left.add(-x);
            }
            else {
                right.add(x);
            }
        }

        left.sort(Collections.reverseOrder());
        right.sort(Collections.reverseOrder());

        int maxDistance = 0;
        int result = 0;

        if (!left.isEmpty() && !right.isEmpty()) {
            maxDistance = Math.max(left.get(0), right.get(0));
        } else if (!left.isEmpty()) {
            maxDistance = left.get(0);
        } else if (!right.isEmpty()) {
            maxDistance = right.get(0);
        }

        for (int i = 0; i < left.size(); i += m) {
            result += left.get(i) * 2;
        }

        for (int i = 0; i < right.size(); i += m) {
            result += right.get(i) * 2;
        }

        System.out.println(result - maxDistance);
    }

}
