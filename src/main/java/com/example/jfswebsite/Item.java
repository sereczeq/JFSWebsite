package com.example.jfswebsite;

import jakarta.faces.bean.ManagedBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@ManagedBean
public class Item {
    private int id;

    private ItemType type;

    private String name;

    private int price;

    public Item() {

    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
//        String sql = "UPDATE cart SET name = '" + name + "' WHERE id='" + id + "';";
//
//        Connection connection = makeConnection();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sql);
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
//        String sql = "UPDATE cart SET price = '" + price + "' WHERE id='" + id + "';";
//
//        Connection connection = makeConnection();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sql);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/Shop";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", Env.password);
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
