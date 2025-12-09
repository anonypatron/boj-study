package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PPAP2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(find(br.readLine()));
    }

    private static String find(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= 4) {
                if (sb.substring(sb.length() - 4).equals("PPAP")) {
                    sb.replace(sb.length() - 4, sb.length(), "P");
                }
            }
        }

        return sb.toString().equals("P") ? "PPAP" : "NP";
    }

}
