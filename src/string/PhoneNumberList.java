package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneNumberList { // 5052
    static class Node {
        Node []children = new Node[10];
        boolean isEnd;
    }
    static class Trie {
        private final Node root = new Node();

        public boolean insertNode(String phoneNumber) {
            Node current = root;

            for (int i = 0; i < phoneNumber.length(); i++) {
                int index = Character.getNumericValue(phoneNumber.charAt(i));

                if (current.children[index] == null) {
                    current.children[index] = new Node();
                }
                current = current.children[index];

                if (current.isEnd) {
                    return false;
                }
            }
            current.isEnd = true;

            for (Node node : current.children) {
                if (node != null) {
                    return false;
                }
            }
            return true;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Trie trie = new Trie();
            int n = Integer.parseInt(br.readLine());
            boolean flag = true;

            for (int i = 0; i < n; i++) {
                if (!trie.insertNode(br.readLine())) {
                    flag = false;
                }
            }

            sb.append(flag ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

}
