package dal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * author:
 */
public class DBContext {

    private Connection conn = null;
    private static DBContext instance;
    private final String DB_ACC = "IS1704_ISP391_G6_1";
    private final String DB_PASS = "!mQCUJZ5cdUsZI2sIdX7FGDiJksoGl4Z";
    private final String DB_HOST = "ongbantat.store";
    private final String DB_PORT = "3306";
    private final String DB_NAME = "IS1704_ISP391_G6_1";

    public static DBContext getInstance() {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    private DBContext() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // connection
                String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
                conn = DriverManager.getConnection(connectionString, DB_ACC, DB_PASS);
            }
            //call driver
            System.out.println("connected");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public Connection getConnection() {
        return conn;
    }

    public static void main(String[] args) {
        DBContext.getInstance().getConnection();

    }
}




//mysql://IS1704_ISP391_G6_1:!mQCUJZ5cdUsZI2sIdX7FGDiJksoGl4Z@ongbantat.store:3306/IS1704_ISP391_G6_1
