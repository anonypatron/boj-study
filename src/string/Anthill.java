package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anthill {
    static class TrieNode {
        List<TrieNode> children = new ArrayList<>();
        String value;

        public TrieNode(String value) {
            this.value = value;
        }

        public void addChild(String target) {
            children.add(new TrieNode(target));
        }

        public void sortChildren() {
            children.sort((o1, o2) -> {
                return o1.value.compareTo(o2.value);
            });
        }

        TrieNode getChild(String target) {
            for (TrieNode child : children) {
                if (child.value.equals(target)) {
                    return child;
                }
            }
            return null;
        }

    }

    static class Trie {
        TrieNode root = new TrieNode("");

        public void insertNode(String []info) {
            TrieNode current = root;

            for (String s : info) {
                TrieNode next = current.getChild(s);
                if (next == null) {
                    current.addChild(s);
                    next = current.getChild(s);
                }
                current = next;
            }
        }

        public void print(TrieNode root, int depth) {
            root.sortChildren();
            for (TrieNode child : root.children) {
                System.out.println("--".repeat(depth) + child.value);
                print(child, depth + 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        while (n-- > 0) {
            String[] words = br.readLine().split(" ");
            trie.insertNode(Arrays.copyOfRange(words, 1, words.length));
        }

        trie.print(trie.root, 0);
    }

}
