package code.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private File file;

    public Dao(File file) {
        this.file = file;
    }

    public List<String[]> selectAll() {
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

    public List<String[]> selectWithCondition(String condition) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> employees = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(cvsSplitBy);
                if (checkCondition(condition, employee)) {
                    employees.add(employee);
                }
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

    private boolean checkCondition(String condition, String[] employee) {
        String[] conditionArray = condition.split("<|>|=|<>");

        String columnName = conditionArray[0];
        String conditionSign = chooseConditionSign(condition);
        String conditionValue = conditionArray[1].trim();

        switch (conditionSign) {
            case ">":
                return Integer.valueOf(employee[3]) > Integer.valueOf(conditionValue);
            default:
                return false;
        }

    }

    private String chooseConditionSign(String condition) {
        String[] signs = {">", "<", "=", "<>"};
        String conditinoSign = "";

        for (String sign: signs) {
            if (condition.contains(sign)) {
                conditinoSign = sign;
            }
        }
        return conditinoSign;
    }

}
