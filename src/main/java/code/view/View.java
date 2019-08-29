package code.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class View {

    public String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void typeQuery() {
        System.out.println("Type query: ");
    }

    public void dispayAll(List<String[]> employees) {
        employees.stream()
                .forEach(employee -> System.out.println(Arrays.toString(employee)));
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayColumns(List<String[]> employees, List<String> trimmedColumnsNames) {
        String[] headings = employees.get(0);

//        String displayColumnValue = trimmedColumnsNames.stream()
//                .filter(columnName -> Arrays.asList(headings).contains(columnName))
//                .collect(Collectors.joining(" "));
//
//        System.out.println(displayColumnValue);

        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < headings.length; i++) {
            for (int j = 0; j < trimmedColumnsNames.size(); j++) {
                if (headings[i].equals(trimmedColumnsNames.get(j))) {
                    indexes.add(j);
                }
            }
        }

        for (String[] employee : employees) {
            for (int indx: indexes) {
                System.out.print(employee[indx] + " ");
            }
            System.out.println();
        }
    }
}

/*
    SELECT * FROM employees

    SELECT ID, first_name,last_name,  age FROM employees

    SELECT * FROM employees WHERE age > 20

 */