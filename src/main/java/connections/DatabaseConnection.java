package connections;

import data.KindOfItem;
import data.Market;
import data.abstracts.Thing;
import data.things.Car;
import data.things.Guitar;
import data.things.Watch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";

    private static final String Product = "VSU_LAB.Product";
    private static final String Cars = "VSU_LAB.Cars";
    private static final String Guitars = "VSU_LAB.Guitars";
    private static final String Watches = "VSU_LAB.Watches";

    //region Queries
    private static final String insertProduct   =
            "INSERT INTO " + Product + "(id, title, price) VALUES(?,?,?)";
    private static final String insertCar      =
            "INSERT INTO " + Cars + "(car_id, hp) VALUES(?,?)";
    private static final String insertGuitar   =
            "INSERT INTO " + Guitars + "(guitar_id, strings) VALUES(?,?)";
    private static final String insertWatch      =
            "INSERT INTO " + Watches + "(watch_id, clocks) VALUES(?,?)";
    private static final String clearTable      =
            "DELETE FROM VSU_LAB.Product";

    private static final String getAllGuitars     =
            "SELECT * " +
            "FROM " + Product +" JOIN " + Guitars +
            " ON " + Product + ".id = VSU_LAB.Guitars.guitar_id";

    private static final String getAllCars   =
            "SELECT * " +
            "FROM " + Product + " JOIN " + Cars +
            " ON " + Product + ".id = VSU_LAB.Cars.car_id";

    private static final String getAllWatches      =
            "SELECT * " +
            "FROM " + Product +" JOIN " + Watches +
            " ON " + Product + ".id = VSU_LAB.Watches.watch_id";
    //endregion

    // Connect with database
    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(DB_URL, "sa", "");
    }

    // Execute simple query without result set
    private static void executeQuery(String query) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();
        statement.execute(query);

        statement.close();
        connection.close();
    }

    // Clear table
    private static void clearTable() throws SQLException, ClassNotFoundException {
        executeQuery(clearTable);
    }

    // Insert product to database
    private static void insert(Thing product) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(insertProduct);

        statement.setLong(1, product.getId());
        statement.setString(2, product.getTitle());
        statement.setDouble(3, product.getPrice());
        statement.executeUpdate();

        if (product instanceof Car) {
            statement = connection.prepareStatement(insertCar);
        }
        else if (product instanceof Watch) {
            statement = connection.prepareStatement(insertWatch);
        }
        else if (product instanceof Guitar) {
            statement = connection.prepareStatement(insertGuitar);
        }

        statement.setLong(1, product.getId());
        statement.setInt(2, product.getSpecific());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    //region Get all products

    // Get all records in database
    private static List<Thing> getAll() throws SQLException, ClassNotFoundException {
        List<Thing> products = new ArrayList<>();
        Connection connection = getConnection();

        products.addAll(getAllByType(connection, KindOfItem.CAR));
        products.addAll(getAllByType(connection, KindOfItem.GUITAR));
        products.addAll(getAllByType(connection, KindOfItem.WATCH));

        connection.close();
        return products;
    }

    private static List<Thing> getAllByType(Connection connection, KindOfItem kind) throws SQLException {
        List<Thing> result = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(getAllCars);
        String s = "hp";
        switch (kind){
            case GUITAR:
                statement = connection.prepareStatement(getAllGuitars);
                s = "strings";
                break;
            case WATCH:
                statement = connection.prepareStatement(getAllWatches);
                s = "clocks";
                break;
            case CAR:
            case UNKNOWN:
                break;
        }
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("title");
            double price = rs.getDouble("price");
            int specific = rs.getInt(s);

            Thing one = new Car(id, name, price, specific);
            switch (kind){
                case GUITAR:
                    one = new Guitar(id, name, price, specific);
                    break;
                case WATCH:
                    one = new Watch(id, name, price, specific);
                    break;
                case CAR:
                case UNKNOWN:
                    break;
            }
            result.add(one);
        }
        statement.close();
        return result;
    }

    //endregion

    public static boolean loadFromDatabase(Market market) throws SQLException, ClassNotFoundException {
        return market.reloadMarketByForeignList(getAll());
    }

    // Save info to database
    public void saveToDatabase(List<Thing> list) throws SQLException, ClassNotFoundException {
        clearTable();
        for (Thing t: list) {
            insert(t);
        }
    }
}
