package org.jlagp.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJava {
    private static String url = "jdbc:mysql://localhost:3306/jdbc";
    private static String user = "luis";
    private static String pass = "pepe";
    private static Connection conn;

    public static Connection getInstance() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, pass);
        }
        return conn;
    }
}
