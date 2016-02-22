package com.jdm5908_bw.ist402.databasetester;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
    public static final DBConnection INSTANCE = new DBConnection();
    private static Connection connection;
    private static final String path = "jdbc:jtds:sqlserver://htp://johnserver.cloudapp.net:1433/HTA;instance=SQLEXPRESS;integratedSecurity=true;";
    private static ArrayList<String> list = new ArrayList<>();

    private DBConnection(){
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> logIn(){
        try {
            Statement stmt = connection.createStatement();
            String query = "select * from guest.Test_Table";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                int userID = rs.getInt("UserID");
                String fName = rs.getString("FirstName");
                String lName = rs.getString("LastName");
                Date date = rs.getDate("Date");
                String row = userID + "\t" + fName + "\t" + lName + "\t" + date;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
