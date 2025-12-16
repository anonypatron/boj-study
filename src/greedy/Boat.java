package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boat { // 1092
    // 내림차순으로 정렬 후 무게를 많이 들 수 있는 크레인부터 하나씩 들기
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> crains = new ArrayList<>();
        List<Integer> boxes = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crains.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        crains.sort(Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        if (crains.get(0) < boxes.get(0)) {
            System.out.print("-1");
            return;
        }

        int count = 0;
        while (!boxes.isEmpty()) {
            for (Integer crain : crains) {
                for (int i = 0; i < boxes.size(); i++) {
                    if (crain >= boxes.get(i)) {
                        boxes.remove(i);
                        break;
                    }
                }
            }
            count++;
        }

        System.out.print(count);
    }

}
