package application;

import application.enums.DeleteOptions;
import application.enums.MenuItem;
import application.enums.Ordering;
import exceptions.UserInputException;
import utils.ConvertorsAndChecks;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Dialog {
    private String lastQuery = "1";

    String getLastQuery() {
        return lastQuery;
    }

    void printMenu() {
        System.out.println("\t* VsuJavaShop *\t");
        System.out.println("1\t Print Products");
        System.out.println("2\t Delete Product (buy)");
        System.out.println("3\t Add Product");
        System.out.println("4\t Load info from text-file");
        System.out.println("5\t Load info from database");
        System.out.println("6\t Save info to file");
        System.out.println("7\t Save info to database");
        System.out.println("8\t Edit item");
        System.out.println("0\t Exit");
    }

    String printMsgAndGetInput() {
        return printMsgAndGetInput("please, input number -> ");
    }

    String printMsgAndGetInput(String msg){
        System.out.println(msg);
        Scanner reader = new Scanner(System.in);
        lastQuery = reader.nextLine();
        return lastQuery;
    }

    void printMsg(String msg){
        System.out.println(msg);
    }

    void showFormatMessage(String msg, int newLinesCount){
        System.out.print(msg);
        for (int i = 0; i < newLinesCount; ++i){
            System.out.println();
        }
    }

    private int checkValueAndGetNaturalOrZeroResult(String input){
        if (!ConvertorsAndChecks.isNaturalDigitString(input))
            throw new UserInputException("Пользователь ввел не натуральное число");

        return Integer.parseInt(input);
    }

    public MenuItem getUserAction(String input) {
        try {
            int menuItem = checkValueAndGetNaturalOrZeroResult(input);
            switch (menuItem){
                case 1:
                    return MenuItem.PRINT;
                case 2:
                    return MenuItem.DELETE;
                case 3:
                    return MenuItem.ADD;
                case 4:
                    return MenuItem.LOAD_FROM_TEXT_FILE;
                case 5:
                    return MenuItem.LOAD_FROM_DATABASE;
                case 6:
                    return MenuItem.SAVE_TO_TEXT_FILE;
                case 7:
                    return MenuItem.SAVE_TO_DATABASE;
                case 8:
                    return MenuItem.EDIT;
                case 0:
                    return MenuItem.EXIT;
                default:
                    return MenuItem.NOT_IN_RANGE;
            }
        }
        catch (UserInputException e){
            return MenuItem.ERROR_INPUT;
        }
    } // ... func getUserAction()

    private boolean isCorrectParametricInput(String input) {
        if (!input.contains("--")){
            return false;
        }

        String[] parts = input.split("--");
        parts[0] = parts[0].trim();
        if (parts.length != 2) {
            return false;
        }

        if (parts[0].length() > 1 ||
                !(parts[0].charAt(0) >= '0' && parts[0].charAt(0) <= '9'))
            return false;

        parts = parts[1].split("=");
        return parts[0].trim().length() != 0 && parts[1].trim().length() == 1;
    }

    boolean isYesAnswer(String msg){
        String answer = printMsgAndGetInput(msg + "\n--> yes/no ? (y/n)");
        return answer.equals("yes") || answer.equals("y");
    }

    public DeleteOptions deleteCheckOptions(String s, List<String> res) {
        res.clear();
        if (ConvertorsAndChecks.isNaturalDigitString(s)) {
            res.add(s);
            return DeleteOptions.SINGLE;
        }

        if (s.contains("-")) {
            String[] lines = s.split("-");
            for (int i = 0; i < lines.length; ++i){
                lines[i] = lines[i].trim();
            }
            if (lines.length == 2 &&
                    ConvertorsAndChecks.isNaturalDigitString(lines[0]) &&
                    ConvertorsAndChecks.isNaturalDigitString(lines[1])) {
                res.add(lines[0]);
                res.add(lines[1]);
                return DeleteOptions.RANGE;
            }

            return DeleteOptions.UNDEFINED;
        }

        DeleteOptions answer = DeleteOptions.UNDEFINED;
        if (!s.contains(",")){
            return answer;
        }

        String[] lines = s.split(",");
        for (int i = 0; i < lines.length; ++i){
            lines[i] = lines[i].trim();
            if (!ConvertorsAndChecks.isNaturalDigitString(lines[i]))
                return answer;
        }

        res.addAll(Arrays.stream(lines).collect(Collectors.toList()));
        return DeleteOptions.DISCRETELY;
    }

    public void printRows(String[] rows) {
        for (String row: rows){
            showFormatMessage(row, 2);
        }
    }
} // ... class Dialog