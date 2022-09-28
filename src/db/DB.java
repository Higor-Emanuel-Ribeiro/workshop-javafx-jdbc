package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    
    private static final String url = "jdbc:mysql://localhost:3306/cousejdbc2";
    private static final String user = "developer";
    private static final String password = "280319";
    
    public static Connection getConnection() {
        
        try {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }  
    
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
		throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
	if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
	}
    }  
}
