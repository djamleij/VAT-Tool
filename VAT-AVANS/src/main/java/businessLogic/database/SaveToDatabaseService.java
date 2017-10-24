package businessLogic.database;

import domain.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SaveToDatabaseService extends DatabaseService {

    public SaveToDatabaseService(String username) {
        connection = null;
        statement = null;
        this.username = username;
    }

    /**
     * saveAllToDatabase lets user save the current list to the local database
     * @param shapes shapes to save
     * @return boolean value for succes of operation
     */
    public boolean saveAllToDatabase (ArrayList<Shape> shapes) {
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
            statement.execute("truncate `shapes`");
            for (Shape shape : shapes) {
                String sql = createSqlQuery(shape);
                if (sql.contains("not recognized")) {
                    return false;
                }
                statement.executeUpdate(sql);
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println("SQL Statement Exception");
            sqle.printStackTrace();
            return false;
        }
        // Close resources
        finally {
            this.closeResources();
        }
    }

    /**
     * creates the query for the shape
     * @param shape shape to create a query for
     * @return created query as String
     */
    private String createSqlQuery (Shape shape) {
        StringBuilder query = new StringBuilder("INSERT INTO shapes VALUES(");

        if (shape instanceof Rhombus) {
            Rhombus rhombus = (Rhombus)shape;
            query.
                    append("'Rhombus', ").
                    append(shape.getVolume()).append(", ").
                    append(rhombus.getLength()).append(", ").
                    append(rhombus.getHeight()).append(", ").
                    append("0.0)");
            return query.toString();
        } else if (shape instanceof Cube) {
            Cube cube = (Cube) shape;
            query.
                    append("'Cube', ").
                    append(cube.getVolume()).append(", ").
                    append(cube.getLength()).append(", ").
                    append(cube.getHeight()).append(", ").
                    append(cube.getWidth()).append(")");
            return query.toString();
        } else if (shape instanceof Cylinder) {
            Cylinder cylinder = (Cylinder) shape;
            query.
                    append("'Cylinder', ").
                    append(cylinder.getVolume()).append(", ").
                    append(cylinder.getRadius()).append(", ").
                    append(cylinder.getHeight()).append(", ").
                    append("0.0)");
            return query.toString();
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            query.
                    append("'Rectangle', ").
                    append(rectangle.getVolume()).append(", ").
                    append(rectangle.getLength()).append(", ").
                    append(rectangle.getWidth()).append(", ").
                    append("0.0)");
            return query.toString();
        } else if (shape instanceof Sphere) {
            Sphere sphere = (Sphere) shape;
            query.
                    append("'Sphere', ").
                    append(sphere.getVolume()).append(", ").
                    append(sphere.getRadius()).append(",").
                    append("0.0, 0.0)");
            return query.toString();
        }
        return "ShapeNotRecognizedString";
    }
}