package code.view;

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

    public void dispayAll (List<String[]> employees) {
        employees.stream()
                .forEach(employee -> System.out.println(Arrays.toString(employee)));
//        for (String[] employee : employees) {
//            System.out.println(Arrays.toString(employee));
//        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}

/*
    SELECT * FROM employees

    SELECT ID, first_name,last_name,  age FROM employees

 */