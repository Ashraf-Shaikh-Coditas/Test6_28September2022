package Test6_28Sep2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Formatter;

public class AuthorCRUD {
    public void createTable() {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            String sql = "create table Author(aid int primary key auto_increment,name varchar(20))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("TABLE CREATED SUCCESSFULLY");
    }

    public void insertAuthor() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the number of authors to be added : ");
            int numOfAuthors = Integer.parseInt(bufferedReader.readLine());
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            for (int i = 0; i < numOfAuthors; i++) {
                String sql = "Insert into Author(name) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                System.out.println("Enter the name of author to be added : ");
                String name = bufferedReader.readLine();
                preparedStatement.setString(1, name);
                preparedStatement.execute();
            }

            System.out.println("AUTHORS ADDED SUCCESSFULLY ...");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAuthor() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            System.out.println("Enter the id of author to be updated : ");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the new name  for author to be updated : ");
            String newName = bufferedReader.readLine();


            String sql = "update author set name =(?) where aid = " + id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.execute();

            System.out.println("RECORD UPDATED SUCCESSFULLY ..");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuthor() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            System.out.println("Enter the id of author to be deleted : ");
            int id = Integer.parseInt(bufferedReader.readLine());

            String sql = "delete from author  where aid = " + id+" and author.aid = book.bid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            System.out.println("RECORD DELETED SUCCESSFULLY ..");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchRecords() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Formatter formatter = new Formatter();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            String sql = "select * from author";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("AUTHOR DETAILS : ");
            formatter.format("%15s %20s\n", "ID", "Author Name");
            while (resultSet.next()) {
                formatter.format("%15s %20s\n", resultSet.getInt(1), resultSet.getString(2));
            }
            System.out.println(formatter);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {
        AuthorCRUD a = new AuthorCRUD();
//        a.createTable();
//        a.insertAuthor();
//        a.updateAuthor();
//        a.deleteAuthor();
        a.fetchRecords();
    }
}
