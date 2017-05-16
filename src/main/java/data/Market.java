package data;

import utils.IdGenerator;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Market{

    /* Закрытые поля и методы */

    private TreeMap<Long, Thing> things;
    private IdGenerator generator;              // объект, присваивающий товарам новый id
    private String fileName;

    /**
     * функция добавляет уже сформированный товар в коллекцию
     * @param th новый товар коллекции
     */
    private void addInternalItem(Thing th) {
        things.put(th.getId(), th);
    }

    /* Свойства */

    public int count() {
        return things.size();
    }

    /* Конструкторы */

    public Market() {
        things = new TreeMap<Long, Thing>();
        generator = new IdGenerator();
        fileName = "market_log.txt";
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
        if (things.containsKey(idOfThing))
            return false;

        things.put(idOfThing, thing);
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
        if (!things.containsKey(i))
            return null;

        return things.get(i);
    }


    public boolean deleteItemById(long id) {
        // если множество сейчас не содержит такой элемент, возвращаем false -- не удалось удалить
        if (!things.containsKey(id))
            return false;

        things.remove(id);
        return true;
    }


    public String[] serializeToStrings(){
        List<String> res = things.values().
                stream().
                map(Thing::getInfoAboutMe).
                collect(Collectors.toList());
        return res.toArray(new String[res.size()]);
    }

    public boolean saveToTextFile() {
        String[] output = serializeToStrings();

        return true;
    }

}
