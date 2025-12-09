package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 정렬을 한다.
 * 2. 처음 부터 끝까지에 대해
 *  2-1. 하나(a)를 잡고, 앞에서 부터 다른 하나를 찾음(b)
 *  2-2. a - b에 해당하는 값을 이분 탐색으로 찾는다.
 *  2-3. 값이 있으면 ok.
 * 3. 카운트 출력
 */
public class Good {
    static int []array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int count = 0;
        for (int i = 0; i < n; i++) {
            int x = array[i];

            int left = 0, right = n - 1;
            while (left < right) {
                int sum =  array[left] + array[right];

                if (sum > x) {
                    right--;
                }
                else if (sum < x) {
                    left++;
                }
                else {
                    if (left != i && right != i) {
                        count++;
                        break;
                    }
                    else if (left == i) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }

        System.out.print(count);
    }

}
