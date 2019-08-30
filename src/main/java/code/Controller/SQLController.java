package code.Controller;

import code.dao.Dao;
import code.view.View;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

            if (userInput.contains("WHERE")) {
                if (trimmedColums.contains("*") && trimmedColums.size() == 1) {
                    String[] tempArr = userInput.split("WHERE");
                    String condition = tempArr[1];
                    try {
                        view.dispayAll(dao.selectWithCondition(condition));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (trimmedColums.contains("*") && trimmedColums.size() == 1) {
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
        List<String> columnHeadingsInCSV = Arrays.asList(TempColumnHeadingsInCSV);

        Optional<String> posibleError = trimmedColums.stream()
                .filter(columnHeading -> !columnHeadingsInCSV.contains(columnHeading))
                .findAny();

        if (posibleError.isPresent()) {
            view.displayMessage("Wrong input. One or more columns doesn't exist");
            return false;
        }
        return true;
    }

    private boolean checkUserInputContainsSelectAndFrom(String userInput) {
        return userInput.contains("SELECT") || userInput.contains("FROM");
    }
}
