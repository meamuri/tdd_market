package application;

public class Application {
    public void run(){
        Dialog dialog = new Dialog();

        MenuItem menu_item = MenuItem.ERROR_INPUT;
        do {
            dialog.printMenu();
            menu_item = dialog.getMenuItemDialog();
            switch (menu_item){
                case PRINT:
                    break;
                case BUY:
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
        dialog.showFormatMessage("Thx, bye! App ");
    }
}
