package application;

import data.KindOfItem;
import data.Market;
import data.Resources;
import utils.ConvertorsAndChecks;

public class Application {
    private Dialog dialog;
    private Market market;

    public Application() {
        dialog = new Dialog();
        market = new Market();
    }

    public void run() {
        MenuItem menu_item;
        do {
            dialog.printMenu();
            menu_item = dialog.getUserAction(dialog.printMsgAndGetInput());
            switch (menu_item){
                case PRINT:
                    Print();
                    break;
                case BUY:
                    Buy();
                    break;
                case ADD:
                    Add();
                    break;
                case LOAD_FROM_TEXT_FILE:
                    LoadFromTextFile();
                    break;
                case LOAD_FROM_DATABASE:
                    LoadFromDataBase();
                    break;
                case SAVE_TO_TEXT_FILE:
                    SaveToTextFile();
                    break;
                case SAVE_TO_DATABASE:
                    SaveToDataBase();
                    break;
                case ERROR_INPUT:
                    PrintAboutErrorInput();
                    break;
                case EXIT:
                    break;
            }
        } while (menu_item != MenuItem.EXIT);
        dialog.showFormatMessage("Thx, bye! App ", 1);
    }

    private void PrintAboutErrorInput() {
    }

    private void SaveToDataBase() {
    }

    private void SaveToTextFile() {
    }

    private void LoadFromDataBase() {
    }

    private void LoadFromTextFile() {
        
    }

    private void Add() {
        String type = dialog.printMsgAndGetInput(Resources.inviteForInputObjectKind);
        if (!ConvertorsAndChecks.isNaturalDigitString(type)) {
            dialog.showFormatMessage("Введено некорректное значение!", 1);
            return;
        }

        KindOfItem kind = Resources.getTypeOfItem(Integer.parseInt(type));
        if (kind == KindOfItem.UNKNOWN){
            dialog.showFormatMessage("Неизвестный тип товара", 1);
            return;
        }

        String title = dialog.printMsgAndGetInput("Введите название товара");
        String price = dialog.printMsgAndGetInput("Введите цену товара");
        if (!ConvertorsAndChecks.isDoubleDigit(price)){
            dialog.showFormatMessage("Цена товара введена некорректно!", 1);
            return;
        }
        Double p = Double.parseDouble(price);
        if (p < 0) {
            dialog.showFormatMessage("Цена товара не может быть отризцательной!", 1);
            return;
        }

        String info = dialog.printMsgAndGetInput(Resources.inviteForInputSpecialInfo(kind));
        if (!ConvertorsAndChecks.isNaturalDigitString(info)){
            dialog.showFormatMessage("Цена товара введена некорректно!", 1);
            return;
        }


        Integer inf = Integer.parseInt(info);
        market.addItemToMarket(title, p, inf, kind);
    }

    private void Print(){
        String[] rows = market.serializeToStrings();
        for (String row: rows){
            dialog.printMsg(row);
        }
    }

    private void Buy(){

    }


}
