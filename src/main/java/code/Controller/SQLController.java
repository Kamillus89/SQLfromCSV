package code.Controller;

import code.dao.Dao;
import code.view.View;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SQLController {

    Dao dao;
    View view;

    public SQLController(Dao dao, View view) {
        this.dao = dao;
        this.view = view;
    }

    public void validateQuery(String userInput) {
        if (checkUserInputContainsSelectAndFrom(userInput)) {
            String[] tempArrayStr = userInput.split("FROM");
            String[] splitBySelect = tempArrayStr[0].split("SELECT");
            String[] columns = splitBySelect[1].split(",");

            List<String> trimmedColums = Stream.of(columns)
                    .map(String::trim)
                    .collect(Collectors.toList());

            if (trimmedColums.contains("*") && trimmedColums.size() == 1) {
                view.dispayAll(dao.selectAll());
            } else {
                view.displayMessage("wrong input");
            }
        } else {
            view.displayMessage("wrong input");
        }

    }

    private boolean checkUserInputContainsSelectAndFrom(String userInput) {
        return userInput.contains("SELECT") || userInput.contains("FROM");
    }
}
