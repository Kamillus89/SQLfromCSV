package code.view;

import java.util.Arrays;
import java.util.List;

public class View {

    public void dispayAll (List<String[]> employees) {
        for (String[] employee : employees) {
            System.out.println(Arrays.toString(employee));
        }
    }
}
