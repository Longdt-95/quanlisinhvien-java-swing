/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thaond
 */
public class ConnectDB {

    public static Connection connectSQLServer() throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DB_URL = "jdbc:sqlserver://localhost;";
        String DATABASENAME = "databaseName=managerstudent;";
        String USER = "user=sa;";
        String PASS = "password=saker0905971230";
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
        return connection;
    }
}
