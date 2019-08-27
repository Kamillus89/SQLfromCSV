package code;

import code.view.View;
import code.dao.Dao;

import java.io.File;

public class App
{
    public static void main( String[] args ) {

        Dao dao = new Dao(new File("employees.csv"));
        View view = new View();
        view.dispayAll(dao.selectQuery());
    }
}
