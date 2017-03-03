package application;

import java.util.Scanner;

public class Dialog {
    void printMenu(){
        System.out.println("\t* VsuJavaShop *\t");
        System.out.println("1\t Print Products");
        System.out.println("2\t Buy Product");
        System.out.println("3\t Load info from text-file");
        System.out.println("4\t Load info from database");
        System.out.println("5\t Save info to file");
        System.out.println("6\t Save info to database");
        System.out.println("0\t Exit");
    }

    String printMsgAndGetInput(){
        System.out.println("please, input number -> ");
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    void showFormatMessage(String msg){
        System.out.println(msg);
    }
    void showFormatMessage(String msg, int newLinesCount){
        System.out.print(msg);
        for (int i = 0; i < newLinesCount; ++i){
            System.out.println();
        }
    }

    private Boolean isAnyNumber(String input){
        return isNumber(input.substring(1, input.length())) && (input.charAt(0) == '-' || input.charAt(0) == '+')
                || isNumber(input);
    }
    private Boolean isNumber(String input){
        for (int i = 0; i < input.length(); ++i){
            if (input.charAt(i) < '0' || input.charAt(i) > '9')
                return false;
        }
        return true;
    }

    private int checkValueAndGetNaturalOrZeroResult(String input){
        if (input.length() == 0)
            throw new NumberFormatException("your string is short for number convert!");
        for (int i = 0; i < input.length(); ++i){
            if (input.charAt(i) < '0' || input.charAt(i) > '9')
                throw new NumberFormatException("that is not number in your string!");
        }

        return Integer.parseInt(input);
    }


    public MenuItem getUserAction(String input) {
        try {
            int menuItem = checkValueAndGetNaturalOrZeroResult(input);
            switch (menuItem){
                case 1:
                    return MenuItem.PRINT;
                case 2:
                    return MenuItem.BUY;
                case 3:
                    return MenuItem.LOAD_FROM_TEXT_FILE;
                case 4:
                    return MenuItem.LOAD_FROM_DATABASE;
                case 5:
                    return MenuItem.SAVE_TO_TEXT_FILE;
                case 6:
                    return MenuItem.SAVE_TO_DATABASE;
                case 0:
                    return MenuItem.EXIT;
                default:
                    return MenuItem.NOT_IN_RANGE;
            }
        }
        catch (NumberFormatException e){
            return MenuItem.ERROR_INPUT;
        }

    }
}