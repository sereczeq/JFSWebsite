package com.example.jfswebsite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

import java.util.Properties;

@ManagedBean
@SessionScoped
public class ShoppingCart {

    public ShoppingCart() {
    }

//    private ArrayList<Item> items;

    public ArrayList<Item> getItems() throws SQLException, ClassNotFoundException {
        Connection connection = makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, price FROM cart");
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Item> items = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");

            Item item = new Item();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);

            items.add(item);
        }
        resultSet.close();

        return items;
    }

//    public void setItem(ArrayList<Item> items)
//    {
//        this.items = items;
//    }

    public void AddItem(Item item) throws SQLException, ClassNotFoundException {
        int id = item.getId();
        String name = item.getName();
        int price = item.getPrice();

        String sql = "INSERT INTO cart (id, name, price) values (default, '" + name + "', " + price + ")";

        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public void RemoveItem(Item item) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM cart WHERE id = " + item.getId() + ";";

        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public int FullPrice() throws SQLException, ClassNotFoundException {

        Connection connection = makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT price FROM cart");
        ResultSet resultSet = preparedStatement.executeQuery();

        int total = 0;

        while (resultSet.next()) {
            int price = resultSet.getInt("price");

            total += price;
        }

        resultSet.close();
        return total;
    }

    public void ClearCart() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM cart;";

        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
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

    public void UpdateItem(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE cart SET name = '" + item.getName() + "' WHERE id='" + item.getId() + "';";

        Connection connection = makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        sql = "UPDATE cart SET price = '" + item.getPrice() + "' WHERE id='" + item.getId() + "';";

        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
