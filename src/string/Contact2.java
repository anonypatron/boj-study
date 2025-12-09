package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact2 { // 1013
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String pattern = "(100+1+|01)+";
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            sb.append(br.readLine().matches(pattern) ? "YES" : "NO").append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
