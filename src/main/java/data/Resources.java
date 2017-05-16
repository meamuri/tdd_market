package data;

public class Resources {
    /**
     * Функция определяет, объект какого типа пользователь желает создать
     * @param userInput - число, соответствующее объекту определенного типа
     * @return Перечисление, которое может также имеет специальное значение "неизвестно"
     */
    static public KindOfItem getTypeOfItem(int userInput){
        switch (userInput){
            case 1:
                return KindOfItem.CAR;
            case 2:
                return KindOfItem.WATCH;
            case 3:
                return KindOfItem.GUITAR;
            default:
                return KindOfItem.UNKNOWN;
        }
    }

    public static String inviteForInputObjectKind =
            "\nВведите, товар какого типа вы хотите создать:\n" +
            "1. Автомобиль\n" +
            "2. Часы\n" +
            "3. Гитара\n";

    public static String inviteForInputFileName =
            "Введите имя файла, в который нужно сохранить\n" +
            "Введите точку чтобы использовать прежнее имя";

    public static String inviteForInputSpecialInfo(KindOfItem k) {
        String res = "Введите ";
        switch (k) {
            case CAR:
                return res + "мощность автомобиля в лошадиных силах";
            case WATCH:
                return res + "количество циферблатов на представленной модели";
            case GUITAR:
                return res + "число струн музыкального инструмента";
        }
        return "";
    }
}
