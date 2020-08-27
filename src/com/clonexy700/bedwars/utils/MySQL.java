package com.clonexy700.bedwars.utils;

import com.clonexy700.bedwars.Main;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {
    private Main main;
    private String user = "";
    private String pass = "";
    private String host= "";
    private String db = "";
    private Connection connection;

    public MySQL(Main main, String user, String pass, String host, String db) {
        this.main = main;
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.db = db;

        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306" + db, user, pass);
            Bukkit.getConsoleSender().sendMessage(main.prefix + "§aMySQL Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(main.prefix + "§cMySQL connection error");
        }
    }
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                Bukkit.getConsoleSender().sendMessage(main.prefix + "§aMySQL Connection established");
                return;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            Bukkit.getConsoleSender().sendMessage(main.prefix + "§cMySQL connection error");
        }
    }
    public void update(String qry) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(qry);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }
}
