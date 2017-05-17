package application;

import exceptions.UserInputException;
import utils.ConvertorsAndChecks;

import java.util.Scanner;

public class Dialog {
    void printMenu() {
        System.out.println("\t* VsuJavaShop *\t");
        System.out.println("1\t Print Products");
        System.out.println("2\t Buy Product");
        System.out.println("3\t Add Product");
        System.out.println("4\t Load info from text-file");
        System.out.println("5\t Load info from database");
        System.out.println("6\t Save info to file");
        System.out.println("7\t Save info to database");
        System.out.println("8\t Edit item");
        System.out.println("0\t Exit");
    }

    String printMsgAndGetInput(){
        System.out.println("please, input number -> ");
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    String printMsgAndGetInput(String msg){
        System.out.println(msg);
        Scanner reader = new Scanner(System.in);
        return reader.next();
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
                    return MenuItem.BUY;
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


} // ... class Dialog