package Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneNumberList {
    static class Node {
        Node []children;
        boolean isEnd;
        public Node() {
            children = new Node[10];
            isEnd = false;
        }
    }
    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public boolean insert(String number) {
            Node current = root;

            for (char c : number.toCharArray()) {
                if (current.isEnd) {
                    return false;
                }
                if (current.children[c - '0'] == null) {
                    current.children[c - '0'] = new Node();
                }
                current = current.children[c - '0'];
            }

            return current.isEnd = true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Trie trie = new Trie();
            int n = Integer.parseInt(br.readLine());
            boolean consistency = true;

            for (int i = 0; i < n; i++) {
                consistency = trie.insert(br.readLine());
                if (!consistency) {
                    break;
                }
            }
            sb.append(consistency ? "YES" : "NO").append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
