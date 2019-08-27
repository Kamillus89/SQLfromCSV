package code.Controller;

import code.dao.Dao;
import code.view.View;

public class SQLController {

    Dao dao;
    View view;

    public SQLController(Dao dao, View view) {
        this.dao = dao;
        this.view = view;
    }

    public void validateQuery(String userInput) {
        if ((userInput.equals("SELECT * FROM employees"))) {
            view.dispayAll(dao.selectAll());
        } else {
            view.displayMessage("wrong input");
        }
    }
}
