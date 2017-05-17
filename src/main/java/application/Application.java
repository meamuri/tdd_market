package application;

import application.enums.DeleteOptions;
import application.enums.MenuItem;
import application.enums.OptionContainer;
import application.enums.Ordering;
import data.KindOfItem;
import data.Market;
import data.Resources;
import utils.ConvertorsAndChecks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
            switch (menu_item) {
                case PRINT:
                    specificPrint();
                    break;
                case DELETE:
                    Delete();
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

    private void specificPrint() {
        String options = dialog.printMsgAndGetInput(Resources.infoAboutOptions);
        OptionContainer c = new OptionContainer(options);
        String[] list = market.serializeWithOptions(c);
        dialog.printRows(list);
    }

    private void PrintAboutErrorInput() {
        dialog.printMsg("Неправильно выбран пункт меню!");
    }

    private void SaveToDataBase() {
    }

    private void SaveToTextFile() {
        String name = dialog.printMsgAndGetInput(Resources.inviteForInputFileName(market.getFileName()));
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
        dialog.printRows(market.serializeToStrings());
    }

    private void Delete(){
        Print();
        String input = dialog.printMsgAndGetInput(Resources.infoAboutDeleting);
        List<String> list = new ArrayList<>();
        DeleteOptions opts = dialog.deleteCheckOptions(input, list);

        switch (opts) {
            case UNDEFINED:
                dialog.printMsg("Введенная строка не попадает под шаблон ввода!");
                return;
            case SINGLE:
                if (market.deleteItemById(Long.parseLong(input))){
                    dialog.printMsg("Удаление прошло успешно!");
                    if (dialog.isYesAnswer("Напечатать получившийся список?")){
                        Print();
                    }
                } else {
                    dialog.printMsg("Не удалось удалить элемент, убедитесь в корректности id");
                }
                return;

            // После этих кейсов не происходит выход из программы
            case RANGE:
                Long begin = Long.parseLong(list.get(0));
                Long end = Long.parseLong(list.get(1));
                market.deleteByRange(begin, end);
                break;
            case DISCRETELY:
                LinkedList<Long> ids = list.stream()
                        .map(Long::parseLong)
                        .collect(Collectors.toCollection(LinkedList::new));
                market.deleteByRange(ids);
                break;
        }
        if (dialog.isYesAnswer("Товары, удовлетворяющие заданным критериям, удалены\n" +
                "Распечатать результирующий список?"))
            Print();
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
