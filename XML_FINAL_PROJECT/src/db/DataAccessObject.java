/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khang
 */
public class DataAccessObject implements Serializable {

    private final ResourceBundle rb = ResourceBundle.getBundle("db.db_config", Locale.getDefault());
    private Connection conn = null;
    private final String DB_USERNAME = rb.getString("DB_USERNAME");
    private final String DB_PASSWORD = rb.getString("DB_PASSWORD");
    private final String SERVER_NAME = rb.getString("SERVER_NAME");
    private final String PORT = rb.getString("PORT");
    private final String DATABASE_NAME = rb.getString("DATABASE_NAME");
    private String connectionString = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT + ";databaseName = " + DATABASE_NAME;

    public DataAccessObject() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //open db connection
        conn = DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection();
    }

    public Connection getConn() {
        return conn;
    }

    public boolean executeUpdateSQLwithParams(String sqlUpdate, String... params) throws SQLException {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            int paramsCount = 0;
            for (String param : params) {
                ps.setString(paramsCount, param);
                paramsCount++;
            }
            ps.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    System.err.println(excep.getMessage());
                }
            }
            return false;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean executeInsertSQLwithParams(String sqlInsert, String... params) throws SQLException {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
            int paramsCount = 0;
            for (String param : params) {
                ps.setString(paramsCount, param);
                paramsCount++;
            }
            ps.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    System.err.println(excep.getMessage());
                }
            }
            return false;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public ResultSet getResulSet(String sqlSelect) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sqlSelect);
        return ps.executeQuery();
    }
}
