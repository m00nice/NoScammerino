package services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmailRepository {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "m00nice", "Totodile2305");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String fetchEmail() throws SQLException {
        statement.execute("USE EMAILS");
        statement.execute("SELECT EMAIL FROM EMAIL");
        String email = statement.getResultSet().getString(1);
        return email;
    }


}

