package data;

import Utils.IdGenerator;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Market{

    /* Закрытые поля и методы */

    private LinkedList<Thing> things;           // объект-коллекция всех товаров магазина
    private LinkedHashSet<Long> ids;            // объект, содержащий информацию об уже использованных id
    private IdGenerator generator;              // объект, присваивающий товарам новый id

    /**
     * функция добавляет уже сформированный товар в коллекцию
     * @param th новый товар коллекции
     */
    private void addInternalItem(Thing th) {
        ids.add(th.getId());
        things.add(th);
    }

    /* Свойства */

    public int count() {
        return things.size();
    }

    /* Конструкторы */

    public Market() {
        things = new LinkedList<Thing>();
        ids = new LinkedHashSet<Long>();
        generator = new IdGenerator();
    }

    /* Публичные методы */

    /**
     * Функция добавляет новый товар, заранее проверяя, что
     * товара с таким id еще не существует
     * @param thing товар, возможно с неуникальным для хранимой коллекции id
     * @return результат добавление: True, если удалось добавить
     */
    private boolean add(Thing thing) {
        long idOfThing = thing.getId();
        if (ids.contains(idOfThing))
            return false;

        things.add(thing);
        ids.add(idOfThing);
        return true;
    }

    /**
     * Добавление нового товара на рынок по данным, полученным через пользовательский ввод
     * id товара присваивается автоматически внутренним генератором
     * @param title Название нового товара
     * @param price Его цена
     * @param param Некоторый уникальный параметр
     * @param kind  Тип создаваемого объекта
     * @return      Результат присваивания. Так, может вернуть False,
     *              если тип объекта указан как неизвестный. True в случае успешного добавления
     */
    public boolean addItemToMarket(String title, double price, int param, KindOfItem kind) {
        Thing th = null;
        long id = generator.getNextId();
        switch (kind){
            case CAR:
                th = new Car(id, title, price, param);
                break;
            case GUITAR:
                th = new Guitar(id, title, price, param);
                break;
            case WATCH:
                th = new Watch(id, title, price, param);
                break;

            case UNKNOWN:
                return false;
        }
        addInternalItem(th);
        return true;
    }

    public Thing getItemById(long i) {
        if (!ids.contains(i))
            return null;

        Thing res = null;
        for (Thing thing: things){
            if (i == thing.getId()) {
                res = thing;
                break;
            }
        }

        return res;
    }


    public boolean deleteItemById(long id) {
        // если множество сейчас не содержит такой элемент, возвращаем false -- не удалось удалить
        if (!ids.contains(id))
            return false;

        int k = 0;
        for (Thing thing: things) {
            if (id == thing.getId()) {
                break;
            }
            ++k;
        }

        things.remove(k);
        ids.remove(id);
        return true;
    }


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

}
