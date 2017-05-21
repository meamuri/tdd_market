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

    final public static String infoAboutDeleting = "Какие товары требуется удалить?\n" +
            "Введите id в следующем формате:\n"+
            "Число\t\t чтобы удалить единственный эл-т (н-р: 15).\n" +
            "Диапазон\t\t чтобы удалить по диапазону id (н-р: 15-34).\n"+
            "Перечисление\t\t чтобы удалить выборочные id (н-р: 13, 15, 44).\n";


    final public static String inviteForInputObjectKind =
            "\nВведите, товар какого типа вы хотите создать:\n" +
            "1. Автомобиль\n" +
            "2. Часы\n" +
            "3. Гитара\n";

    public static String inviteForInputFileName(String fileName) {
        return "Введите имя файла, в который нужно сохранить\n" +
                "Введите точку чтобы использовать прежнее имя\n"+
                "Прежнее имя файла: " + fileName;
    }

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

    final public static String infoAboutOptions = "Введите список опций в формате:\n" +
            "opt1:param && opt2:param=val && opt3:param\n" +
            "Сейчас доступны следующие опции и из параметры:\n" +
            "ord (сортировка) : i (по id), t (по названию), p (по цене)\n" +
            "ftr (фильтрация) : \n" +
                    "\ttype = [c (только машины) | w (только часы) | g (только гитары)]\n" +
                    "\tmin_price = минимальная цена\n" +
                    "\tmax_price = максимальная цена\n" +
            "Пустая строка -- без опций";

    final public static String errorAboutDatabaseLoad =
            "Ошибка загрузки из базы данных!\n" +
            "Возможно, она содержит некорректные данные.\n";
}
