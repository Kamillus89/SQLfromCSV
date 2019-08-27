package code.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {

    public String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void typeQuery() {
        System.out.println("Type query: ");
    }

    public void dispayAll (List<String[]> employees) {
        for (String[] employee : employees) {
            System.out.println(Arrays.toString(employee));
        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
