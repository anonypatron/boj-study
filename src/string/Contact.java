package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        String pattern = "(100+1+|01)+";
        while (t-- > 0) {
            String target = br.readLine();
            sb.append(target.matches(pattern) ? "YES" : "NO").append("\n");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
