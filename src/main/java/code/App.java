package code;

import code.Controller.SQLController;
import code.view.View;
import code.dao.Dao;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class App {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1ZLJ7lJo0RZcafF1ha6FG4J6mSXEAglVfNfIngt4LRvM";

    public static void main( String[] args ) {

        SheetsServiceUtil sheetsServiceUtil = new SheetsServiceUtil();
        try {
            sheetsService = SheetsServiceUtil.getSheetsService();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        List<String> ranges = Arrays.asList("B2","B5");
        try {
            BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                    .batchGet(SPREADSHEET_ID)
                    .setRanges(ranges)
                    .execute();
            ValueRange costam = readResult.getValueRanges().;
            System.out.println(costam);
            System.out.println(costam.getValues());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dao dao = new Dao(new File("src/main/resources/employees.csv"));
        View view = new View();
        SQLController sqlController = new SQLController(dao,view);

        view.typeQuery();
        sqlController.validateQuery(view.getInputFromUser());
    }
}
