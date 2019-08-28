package code.Controller;

import code.dao.Dao;
import code.view.View;

import java.util.ArrayList;
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
            } else if (trimmedColums.size() >= 1) {
                if (checkIfAllColumnsExistInFile(trimmedColums)) {
                    view.displayColumns(dao.selectAll(), trimmedColums);
                }
            }

        } else {
            view.displayMessage("wrong input");
        }
    }

    private boolean checkIfAllColumnsExistInFile(List<String> trimmedColums) {
        String[] TempColumnHeadingsInCSV = dao.selectAll().get(0);
        List<String> columnHeadingsInCSV = new ArrayList<>();
        for (String column : TempColumnHeadingsInCSV) {
            columnHeadingsInCSV.add(column);
        }

        for (String columnHeading : trimmedColums) {
            if (!columnHeadingsInCSV.contains(columnHeading)) {
                view.displayMessage("Wrong input. One or more columns doesn't exist");
                return false;
            }
        }
        return true;
    }

    private boolean checkUserInputContainsSelectAndFrom(String userInput) {
        return userInput.contains("SELECT") || userInput.contains("FROM");
    }
}
