package com.example.jfswebsite;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;

import java.sql.*;
import java.util.Objects;
import java.util.Properties;

@ManagedBean
@SessionScoped
public class AccountManager {
    public AccountManager(){};

    private boolean isLoggedIn;

    private Account account;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = true;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) throws SQLException, ClassNotFoundException {
        Connection connection = makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, password FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            if(Objects.equals(login, account.getLogin()) && Objects.equals(password, account.getPassword()))
            {
                this.account = account;
                isLoggedIn = true;
            }

        }
    }

    public void CheckAccount(Account account) throws SQLException, ClassNotFoundException {
        Connection connection = makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, password FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");

            if(Objects.equals(login, account.getLogin()) && Objects.equals(password, account.getPassword()))
            {
                this.account = account;
                isLoggedIn = true;
            }

        }
    }

    public void CreateAccount(Account account) throws SQLException, ClassNotFoundException {

        String accountLogin = account.getLogin();
        String accountPassword = account.getPassword();
        String sql = "INSERT INTO users (id, login, password) values (default, '" + accountLogin + "', " + accountPassword + ")";
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

    public void logout() {
        account = null;
        isLoggedIn = false;
    }
}
