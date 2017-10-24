package businessLogic.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class DatabaseService {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    final String password = "";
    String username;

    void closeResources() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("Closing resultset exception");
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("Closing statement exception");
            }
            statement = null;
        }
    }
}
