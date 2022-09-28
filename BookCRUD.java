package Test6_28Sep2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Formatter;

public class BookCRUD {
    public void createTable() {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            String sql = "create table Book(bid int primary key auto_increment,name varchar(20),price int,aid int , foreign key (aid) references Author(aid))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("TABLE CREATED SUCCESSFULLY");

    }

    public void insertBook() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the number of books to be added : ");
            int numOfAuthors = Integer.parseInt(bufferedReader.readLine());
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            for (int i = 0; i < numOfAuthors; i++) {
                String sql = "Insert into Book(name,price,aid) values (?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                System.out.println("Enter the name of book to be added : ");
                String name = bufferedReader.readLine();
                System.out.println("Enter the price of book to be added : ");
                int price = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter the author id of book to be added : ");
                int aid = Integer.parseInt(bufferedReader.readLine());
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, aid);
                preparedStatement.execute();
            }

            System.out.println("BOOKS ADDED SUCCESSFULLY ...");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            System.out.println("Enter the id of book of which price is to be updated : ");
            int id = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Enter the new price  for book to be updated : ");
            int price = Integer.parseInt(bufferedReader.readLine());


            String sql = "update book set price =(?) where bid = " + id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, price);
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

    public void deleteBook() {
        Connection connection;
        PreparedStatement preparedStatement;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            System.out.println("Enter the id of author to be deleted : ");
            int id = Integer.parseInt(bufferedReader.readLine());

            String sql = "delete from author  where aid = " + id;
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

            String sql = "select * from book";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("BOOK DETAILS : ");
            formatter.format("%15s %20s %15s %15s\n", "ID", "Book Name", "Price", "Author ID");
            while (resultSet.next()) {
                formatter.format("%15s %20s %15s %15s\n", resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4));
            }
            System.out.println(formatter);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void OneToMany() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Formatter formatter = new Formatter();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

            String sql = "select author.name,book.name from author,book where author.aid=book.aid";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("ONE TO MANY RELATIONSHIP DETAILS : ");
            formatter.format("%15s %15s\n", "Book Name", "Author Name");
            while (resultSet.next()) {
                formatter.format("%15s %15s\n", resultSet.getString(1), resultSet.getString(2));
            }
            System.out.println(formatter);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {

    }
}
