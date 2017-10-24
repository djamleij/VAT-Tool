package businessLogic.database;

import domain.*;

import java.sql.*;
import java.util.ArrayList;

public class LoadFromDatabaseService extends DatabaseService {

    public LoadFromDatabaseService(String username) {
        connection = null;
        statement = null;
        resultSet = null;
        this.username = username;
    }

    /**
     * getAllFromDatabase gets all the entries currently in the local database
     * @return ArrayList with shapes
     */
    public ArrayList<Shape> getAllFromDatabase () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("MySQL Driver not found");
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/shapes",username, password);
        } catch (SQLException sqle) {
            System.out.println("SQL Connection Exception");
            sqle.printStackTrace();
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SHAPES");

            ArrayList<Shape> shapes = new ArrayList<>();
            while (resultSet.next()) {
                String shape = resultSet.getString(1);
                double length = resultSet.getDouble(2);
                double radius = resultSet.getDouble(3);
                double height = resultSet.getDouble(4);
                double width = resultSet.getDouble(5);
                shapes.add(createShape(shape, radius, height, width,length));
            }
            return shapes;
        } catch (SQLException sqle) {
            System.out.println("SQL Statement Exception");
            sqle.printStackTrace();
        }
        // Close resources
        finally {
            this.closeResources();
        }
        // Something went wrong
        return new ArrayList<>();
    }


    private Shape createShape (String shape, double radius, double height, double width,double length) {
        switch (shape) {
            case "Sphere":
                return new Sphere(radius);
            case "Cube":
                return new Cube(radius, width, height);
            case "Cylinder":
                return new Cylinder(radius, height);
            case "Rectangle":
                return new Rectangle(length, width);
            case "Rhombus":
                return new Rhombus(length, height);
            default:
                return null;
        }
    }
}