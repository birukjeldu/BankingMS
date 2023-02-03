package com.bank;

//import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String passowrd = "12345";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,passowrd);
            Statement statement =  connection.createStatement();
            int id=88;
            int age = 575;
            String name = "roo";
            String sql = "INSERT INTO `student` (`id`, `name`, `age`) VALUES ('"+ id +"', '"+name+"', '"+age+"');";
            statement.execute(sql);
            System.out.println("completted");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }*/
        ArrayList<Customer> listOfCustomer = new ArrayList<>();
        Manager a = new Manager();
       //a.addCustomer();
        UserInterface.loginPage();
    }
}