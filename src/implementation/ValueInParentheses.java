package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ValueInParentheses {
    static class Info {
        char ch;
        int layer;
        public Info(char ch, int layer) {
            this.ch = ch;
            this.layer = layer;
        }
    }
    static class Node {
        int first, last;
        public Node(int first, int last) {
            this.first = first;
            this.last = last;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Info> stack = new ArrayDeque<>();
        List<Node> list = new ArrayList<>();

        String str = br.readLine();
        int currentLayer = 1, result = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '[') {
                stack.addLast(new Info(ch, currentLayer++));
            }
            else if (stack.isEmpty()) {
                System.out.print("0");
                return;
            }
            else {
                if (ch == ')') {
                    if (stack.peekLast().ch == '(') {
                        Info info = stack.pollLast();

                        if (list.get(info.layer + 1) != null) {
                            Node tmp = list.remove(info.layer + 1);
                        }
                    }
                    else {
                        System.out.print("0");
                        return;
                    }
                }
                else if (ch == ']') {
                    if (stack.peekLast().ch == '[') {

                    }
                    else {
                        System.out.print("0");
                        return;
                    }
                }
            }
        }
    }

}
