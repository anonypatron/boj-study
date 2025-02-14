package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PPAP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if (find(s)) {
            System.out.print("PPAP");
        }
        else {
            System.out.print("NP");
        }
    }

    public static boolean find(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= 4) {
                if (sb.substring(sb.length() - 4).equals("PPAP")) {
                    sb.replace(sb.length() - 4, sb.length(), "P");
                }
            }
        }

        return sb.toString().equals("P");
    }

}
