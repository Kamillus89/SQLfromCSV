package code.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private File file;

    public Dao(File file) {
        this.file = file;
    }

    public List<String[]> selectQuery() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> employees = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(cvsSplitBy);
                employees.add(employee);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return employees;
    }

}
