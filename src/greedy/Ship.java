package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ship {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Integer> crains = new ArrayList<>(); ;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            crains.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crains, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        if (crains.get(0) < boxes.get(0)) {
            System.out.print("-1");
            return;
        }

        int answer = 0;
        while (!boxes.isEmpty()) {
            for (int i = 0; i < crains.size(); i++) {
                int currentCrain = crains.get(i);

                for (int j = 0; j < boxes.size(); j++) {
                    if (currentCrain >= boxes.get(j)) { // 들 수 있으면
                        boxes.remove(j);
                        break;
                    }
                }
            }

            answer++;
        }

        System.out.print(answer);
    }

}
