
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
 * @author khang
 */
public class DataAccessObject implements Serializable {
    public static enum MODE_UPDATE {
        LARGE_UPDATE, SMALL_UPDATE
    }

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

    public boolean executeSQLwithParams(String sqlUpdate, MODE_UPDATE MODE, String... params) throws SQLException {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            int paramsCount = 0;
            for (String param : params) {
                ps.setString(paramsCount, param);
                paramsCount++;
            }
            if (MODE == MODE_UPDATE.SMALL_UPDATE)
                ps.executeUpdate();
            else ps.executeLargeUpdate();
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


    public ResultSet getResulSet(String sqlSelect, String... params) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sqlSelect);
        int paramsCount = 0;
        for (String param : params) {
            ps.setString(paramsCount, param);
            paramsCount++;
        }
        return ps.executeQuery();
    }
}
