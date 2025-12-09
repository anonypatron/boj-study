package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearchTree {
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void insertNode(int value) {
            if (value < this.value) {
                if (this.left == null) {
                    this.left = new Node(value);
                }
                else {
                    this.left.insertNode(value);
                }
            }
            else {
                if (this.right == null) {
                    this.right = new Node(value);
                }
                else {
                    this.right.insertNode(value);
                }
            }
        }
    }

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        String str;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            root.insertNode(Integer.parseInt(str));
        }

        postOrder(root);
        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
    }

}
