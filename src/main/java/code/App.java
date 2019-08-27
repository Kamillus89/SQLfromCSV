package code;

import code.Controller.SQLController;
import code.view.View;
import code.dao.Dao;

import java.io.File;

public class App
{
    public static void main( String[] args ) {

        Dao dao = new Dao(new File("src/main/resources/employees.csv"));
        View view = new View();
        SQLController sqlController = new SQLController(dao,view);

        view.typeQuery();
        sqlController.validateQuery(view.getInputFromUser());

    }
}
