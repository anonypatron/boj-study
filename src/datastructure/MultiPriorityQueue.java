package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MultiPriorityQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> treeMap = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                char command = st.nextToken().charAt(0);
                Long value = Long.parseLong(st.nextToken());

                if (command == 'I') {
                    treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);
                }
                else if (!treeMap.isEmpty()) {
                    if (value == -1) {
                        Long min = treeMap.firstKey();
                        treeMap.put(min, treeMap.getOrDefault(min, 1) - 1);
                        if (treeMap.get(min) == 0) {
                            treeMap.remove(min);
                        }
                    }
                    else {
                        Long max = treeMap.lastKey();
                        treeMap.put(max, treeMap.getOrDefault(max, 1) - 1);
                        if (treeMap.get(max) == 0) {
                            treeMap.remove(max);
                        }
                    }
                }
            }

            if (treeMap.isEmpty()) {
                sb.append("EMPTY");
            }
            else {
                sb.append(treeMap.lastKey()).append(' ').append(treeMap.firstKey());
            }
            sb.append("\n");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
