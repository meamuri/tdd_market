package connections;

public class DatabaseConnection {
    private static final String DB_URL =
            "jdbc:h2:" + System.getProperty("user.dir") + "/src/database/DbProducts";

    //region Queries
    private static final String insertProduct   =
            "INSERT INTO Product(id, title, price) VALUES(?,?,?)";
    private static final String insertCar      =
            "INSERT INTO Cars(car_id, hp) VALUES(?,?)";
    private static final String insertGuitar   =
            "INSERT INTO Guitars(guitar_id, strings) VALUES(?,?)";
    private static final String insertWatch      =
            "INSERT INTO Watches(watch_id, clocks) VALUES(?,?)";
    private static final String clearTable      =
            "DELETE FROM Product";

    private static final String getAllBooks     =
            "SELECT * " +
            "FROM Product JOIN Books ON Product.id = Books.book_id";
    private static final String getAllClothes   =
            "SELECT * FROM Product JOIN Clothes ON Product.id = Clothes.clothes_id";
    private static final String getAllFood      =
            "SELECT * FROM Product JOIN Food ON Product.id = Food.food_id";
    //endregion

}
