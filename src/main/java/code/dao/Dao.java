package code.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private File file;
//    private BufferedReader br = null;
    private String line = "";
    private static final String CVS_SPLIT_BY = ",";
    private static final String[] SIGNS = {"<>",">", "<", "="};
    private List<String[]> employees = new ArrayList<>();


    public Dao(File file) {
        this.file = file;
    }

    public List<String[]> selectAll() {
        return getEmployees();
    }

    private List<String[]> getEmployees() {
        BufferedReader br = null;
        try {
            readLineAndAddToEmployeesList();
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

    private void readLineAndAddToEmployeesList() throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            String[] employee = line.split(CVS_SPLIT_BY);
            employees.add(employee);
        }
    }

    public List<String[]> selectWithCondition(String condition) throws IOException {
//        try {
        BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(CVS_SPLIT_BY);
                if (checkCondition(condition, employee)) {
                    employees.add(employee);
                }
            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        return employees;
    }

    private boolean checkCondition(String condition, String[] employee) {
        String[] conditionArray = condition.split("<|>|=|<>");

        String columnName = conditionArray[0];
        String conditionSign = chooseConditionSign(condition);
        String conditionValue = (conditionArray.length <= 2) ? conditionArray[1].trim() : conditionArray[conditionArray.length-1].trim();

        int columnIndex = selectColumntIndex(columnName);

        switch (conditionSign) {
            case ">":
                return Double.valueOf(employee[columnIndex]) > Double.valueOf(conditionValue);
            case "<":
                return Double.valueOf(employee[columnIndex]) < Double.valueOf(conditionValue);
            case "=":
                return Double.valueOf(employee[columnIndex]) == Double.valueOf(conditionValue);
            case "<>":
                return Double.valueOf(employee[columnIndex]) != Double.valueOf(conditionValue);
            default:
                return false;
        }

    }

    private int selectColumntIndex(String columnName) {
        String[] headlines = readFirstLine();
        int index = 0;

        for (int i = 0; i < headlines.length; i++) {
            if (headlines[i].equals(columnName.trim())) {
                index = i;
            }
        }
        return index;

//        return (int) Stream.of(headlines)
//                .filter(headline -> columnName.trim().equals(headline))
//                .count();
    }

    private String[] readFirstLine() {
        int columnAmount = 6;
        String[] headlines = new String[columnAmount];
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            headlines = br.readLine().split(CVS_SPLIT_BY);
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
        return headlines;
    }

    private String chooseConditionSign(String condition) {
        for (String sign : SIGNS) {
            if (condition.contains(sign)) {
                return sign;
            }
        }
        return "";
    }
}