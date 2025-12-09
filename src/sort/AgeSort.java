package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AgeSort {
    static class Person {
        int age;
        String name;
        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            persons.add(new Person(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        persons.stream()
                .sorted((o1, o2) -> o1.age - o2.age)
                .forEach(o -> sb.append(o.age).append(" ").append(o.name).append("\n"));

        System.out.println(sb);
    }
}
