package core.libs;

import java.sql.*;

public class MySQL {
    private static Connection connection;

    public static ResultSet getData(String sql, String iP_DB, String name_DB, String userName_DB, String password_DB) {
        ResultSet rs = null;
        try {
            connection = getConnection(iP_DB + name_DB, userName_DB, password_DB);
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    public static Connection getConnection(String dbURL, String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception e) {
            System.out.println("connect DB failure!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
