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
                case EDIT:
                    Edit();
                    break;
                case NOT_IN_RANGE:
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
        dialog.printMsg("Неправильно выбран пункт меню!");
    }

    private void SaveToDataBase() {
    }

    private void SaveToTextFile() {
        String name = dialog.printMsgAndGetInput(Resources.inviteForInputFileName);
        if (market.saveToFile(name)){
            dialog.printMsg("Сохранение прошло успешно");
        }
        else {
            dialog.printMsg("Сохранение завершилось неудачно");
        }
    }

    private void LoadFromDataBase() {

    }

    private void LoadFromTextFile() {
        if (market.count() == 0 ||
                market.count() > 0 &&
                        dialog.printMsgAndGetInput("вы уверены что хотите перезаписать данные?(y/n)")
                                .equals("y")) {
            if (!market.loadFromFile()) {
                dialog.printMsg("Не удалось загрузить данные из файла");
            }
            else {
                dialog.printMsg("Данные перезаписаны");
                if (dialog.isYesAnswer("Хотите отобразить результат?")){
                    Print();
                }
            }
        }
    }

    private void Add() {
        String type = dialog.printMsgAndGetInput(Resources.inviteForInputObjectKind);
        if (!ConvertorsAndChecks.isNaturalDigitString(type)) {
            dialog.printMsg("Введено некорректное значение!");
            return;
        }

        KindOfItem kind = Resources.getTypeOfItem(Integer.parseInt(type));
        if (kind == KindOfItem.UNKNOWN){
            dialog.printMsg("Неизвестный тип товара");
            return;
        }

        String title = dialog.printMsgAndGetInput("Введите название товара");
        String price = dialog.printMsgAndGetInput("Введите цену товара");
        if (!ConvertorsAndChecks.isDoubleDigit(price)){
            dialog.printMsg("Цена товара введена некорректно!");
            return;
        }
        Double p = Double.parseDouble(price);
        if (p < 0) {
            dialog.printMsg("Цена товара не может быть отрицательной!");
            return;
        }

        String info = dialog.printMsgAndGetInput(Resources.inviteForInputSpecialInfo(kind));
        if (!ConvertorsAndChecks.isNaturalDigitString(info)){
            dialog.showFormatMessage("Цена товара введена некорректно!", 1);
            return;
        }


        Integer inf = Integer.parseInt(info);
        if (market.addItemToMarket(title, p, inf, kind) &&
                dialog.isYesAnswer("Товар добавлен. Показать обновленный список?")){
                Print();
        }
    }

    private void Print(){
        String[] rows = market.serializeToStrings();
        for (String row: rows){
            dialog.showFormatMessage(row, 2);
        }
    }

    private void Buy(){
        Print();
        String input = dialog.printMsgAndGetInput("Введите id товара, который желаете приобрести:");
        if (!ConvertorsAndChecks.isNaturalDigitString(input)) {
            dialog.printMsg("Введенная строка не является натуральным числом!");
            return;
        }

        Long id = Long.parseLong(input);
        if (!market.containItemWithID(id)){
            dialog.printMsg("Товар не найден в списке доступных");
            return;
        }

        if (market.deleteItemById(id) &&
                dialog.isYesAnswer("Товар успешно удален из списка товаров. Напечатать новый список?")) {
            Print();
        }
    }

    private void Edit() {
        Print();
        String input = dialog.printMsgAndGetInput("Введите id товара, который желаете отредактировать:");
        if (!ConvertorsAndChecks.isNaturalDigitString(input)) {
            dialog.printMsg("Введенная строка не является натуральным числом!");
            return;
        }
        Long id = Long.parseLong(input);
        if (!market.containItemWithID(id)){
            dialog.printMsg("Товара с таким айди не существует!");
            return;
        }
        String title = dialog.printMsgAndGetInput("Введите новое название товара");
        String price = dialog.printMsgAndGetInput("Введите новую цену товара");
        if (!ConvertorsAndChecks.isDoubleDigit(price)){
            dialog.printMsg("Цена товара введена некорректно!");
            return;
        }
        Double p = Double.parseDouble(price);
        if (p < 0) {
            dialog.printMsg("Цена товара не может быть отрицательной!");
            return;
        }

        if (!market.editById(id, title, p)){
            dialog.printMsg("Не удалось изменить выбранный товар!");
        }
        else {
            dialog.printMsg("Редактирование прошло успешно!");
            if (dialog.isYesAnswer("Хотите ли посмотреть измененный список")){
                Print();
            }
        }

    }


}
