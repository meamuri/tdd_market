package application;

import data.Market;

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

    private void Print(){

    }

    private void Buy(){

    }


}
