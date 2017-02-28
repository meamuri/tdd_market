package application;

import java.util.Scanner;

enum MenuItem{
    PRINT,
    BUY,
    LOAD_FROM_TEXT_FILE,
    LOAD_FROM_DATABASE,
    SAVE_TO_TEXT_FILE,
    SAVE_TO_DATABASE,

    ERROR_INPUT,
    EXIT,
}

class Dialog {
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

    MenuItem getMenuItemDialog(){
        System.out.println("please, input number -> ");
        try {
            switch (userInputMenuItem()){
                case 1:
                    return MenuItem.BUY;
                case 2:
                    return MenuItem.PRINT;
                case 3:
                    return MenuItem.LOAD_FROM_TEXT_FILE;
                case 4:
                    return MenuItem.LOAD_FROM_DATABASE;
                case 5:
                    return MenuItem.SAVE_TO_TEXT_FILE;
                case 6:
                    return MenuItem.SAVE_TO_DATABASE;
                default:
                    return MenuItem.EXIT;
            }
        }
        catch (NumberFormatException e) {
            return MenuItem.ERROR_INPUT;
        }
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

    private int userInputMenuItem() {
        Scanner reader = new Scanner(System.in);
        String input = reader.next();
        if (!isNumber(input) || input.length() > 1 || input.charAt(0) > '6')
            throw new NumberFormatException("you should input number x in [0..6]");
        return Integer.parseInt(input);
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
}