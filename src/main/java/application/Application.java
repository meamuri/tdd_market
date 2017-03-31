package application;

import data.Manager;
import data.Market;

public class Application {
    private Dialog dialog;
    private Manager manager;

    public Application() {
        dialog = new Dialog();
        manager = new Manager(new Market());
    }

    public void run(){
        MenuItem menu_item;
        do {
            dialog.printMenu();
            menu_item = dialog.getUserAction(dialog.printMsgAndGetInput());
            switch (menu_item){
                case PRINT:
                    // manager.Print();
                    break;
                case BUY:
                    // manager.Print();
                    break;
                case LOAD_FROM_TEXT_FILE:
                    break;
                case LOAD_FROM_DATABASE:
                    break;
                case SAVE_TO_TEXT_FILE:
                    break;
                case SAVE_TO_DATABASE:
                    break;
                case ERROR_INPUT:
                    break;
                case EXIT:
                    break;
            }
        } while (menu_item != MenuItem.EXIT);
        dialog.showFormatMessage("Thx, bye! App ", 1);
    }
}
