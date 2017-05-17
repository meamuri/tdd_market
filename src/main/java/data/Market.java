package data;

import utils.IdGenerator;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Market{

    /* Закрытые поля и методы */

    private TreeMap<Long, Thing> things;
    private IdGenerator generator;              // объект, присваивающий товарам новый id
    private String fileName;
    private String outDirPrefix = "io/";

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
        things = new TreeMap<>();
        generator = new IdGenerator();
        fileName = "out.txt";
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
        while (things.containsKey(id)){
            id = generator.getNextId();
        }
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

    public boolean containItemWithID(Long id) { return things.containsKey(id); }

    public Thing getItemById(long i) {
        if (!things.containsKey(i))
            return null;

        return things.get(i);
    }

    public void deleteByRange(long beginId, long endId){
        for (long i = beginId; i <= endId; ++i){
            things.remove(i);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void deleteByRange(List<Long> ids){
        for (long i: ids){
            things.remove(i);

        }
    }

    public boolean deleteItemById(long id) {
        // если множество сейчас не содержит такой элемент, возвращаем false -- не удалось удалить
        if (!things.containsKey(id))
            return false;

        things.remove(id);
        return true;
    }

    private List<String> serializeToList() {
        return things.values().
                stream().
                map(Thing::getInfoAboutMe).
                collect(Collectors.toList());
    }

    public String[] serializeToStrings(){
        List<String> res = serializeToList();
        return res.toArray(new String[res.size()]);
    }

    public boolean saveToFile() {
        Path path = Paths.get(outDirPrefix + fileName);
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                String str = serializeToList().stream().collect(Collectors.joining("\n"));
                writer.write(str);
            }
        }
        catch (IOException e){
            return false;
        }
        return true;
    }

    public boolean saveToFile(String name) {
        if (name == null || name.equals(fileName) || name.isEmpty()|| name.equals(".")){
            return saveToFile();
        }

        String oldName = fileName;

        fileName = name;
        if (!saveToFile()){
            fileName = oldName;
            return false;
        }

        try {
            File old_f = new File(outDirPrefix + oldName);
            if (old_f.exists())
                Files.delete(old_f.toPath());
        }
        catch (IOException ignored){ }
        return true;
    }

    public boolean loadFromFile() {
        Path path = Paths.get(outDirPrefix + fileName);
        try {
            try (Stream<String> reader = Files.lines(path)) {
                TreeMap<Long, Thing> res = new TreeMap<>();
                List<String> lines = reader.collect(Collectors.toList());
                for(String l: lines){
                    String[] params = l.split("\\|");
                    if (params.length != 5)
                        return false;
                    Thing th = Factory.ThingByRawString(params);
                    if (th == null){
                        return false;
                    }
                    res.put(th.getId(), th);
                }
                this.things = res;
            }
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean editById(long id, String title, double price) {
        if (!things.containsKey(id)){
            return false;
        }
        Thing th = things.get(id);
        th.editMe(title, price);
        return true;
    }

    public boolean editById(long id, String title, double price, int spec) {
        if (!things.containsKey(id)){
            return false;
        }

        things.get(id).editMe(title, price, spec);
        return true;
    }

}
