package Test6_28Sep2022;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("1.Create Author Table" +
                    "\n2.Insert Author" +
                    "\n3.Update Author" +
                    "\n4.Delete Author" +
                    "\n5.Fetch Authors" +
                    "\n6.Create Book Table" +
                    "\n7.Insert Book" +
                    "\n8.Update Book Price" +
                    "\n9.Delete Book Record" +
                    "\n10.Fetch Books " +
                    "\n11. Display One to Many");

            int choice = Integer.parseInt(bufferedReader.readLine());

            switch (choice) {
                case 1 :
                    new AuthorCRUD().createTable();
                    break;
                case 2 :
                    new AuthorCRUD().insertAuthor();
                    break;
                case 3 :
                    new AuthorCRUD().updateAuthor();
                    break;
                case 4 :
                    new AuthorCRUD().deleteAuthor();
                    break;
                case 5 :
                    new AuthorCRUD().fetchRecords();
                    break;
                case 6 :
                    new BookCRUD().createTable();
                    break;
                case 7 :
                    new BookCRUD().insertBook();
                    break;
                case 8 :
                    new BookCRUD().updateBook();
                    break;
                case 9 :
                    new BookCRUD().deleteBook();
                    break;
                case 10 :
                    new BookCRUD().fetchRecords();
                    break;
                case 11 :
                    new BookCRUD().OneToMany();
                    break;
                default:
                    System.exit(0);

            }

        }
    }
}
