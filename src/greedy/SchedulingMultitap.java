package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SchedulingMultitap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int []arr = new int[k];
        int result = 0;
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            int x = arr[i];

            if (set.size() < n || set.contains(x)) {
                set.add(x);
                continue;
            }

            // 1. 멀티탭의 구멍 개수만큼 채워짐(현재 상태)
            // 2. x는 현재 멀티탭에 꽂혀있지 않은 상태임(현재 상태)
            // 3-1. 멀티탭에 꽂혀있는 장치들의 재사용하는 위치를 계산
            // 3-2. 가장 늦게 사용되는 장치를 바꿈(여러개일 경우 아무거나 해도 됨)
            int target = -1, currentlyUse = -1;

            for (int s : set) {
                int nextUseIndex = Integer.MAX_VALUE;
                for (int j = i + 1; j < k; j++) {
                    if (arr[j] == s) {
                        nextUseIndex = j;
                        break;
                    }
                }

                if (nextUseIndex > currentlyUse) {
                    currentlyUse = nextUseIndex;
                    target = s;
                }

            }

            set.remove(target);
            set.add(x);
            result++;
        }

        System.out.print(result);
    }

}
