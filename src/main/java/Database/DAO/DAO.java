package Database.DAO;

import Database.Models.Book;
import Database.Models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO {
    protected Connection connection;

    public List<Book> getBooks() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * " +
                "from public.book limit 50;");

        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        List<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs));
        return books;
    }

    public List<Book> searchBookByAttribute(String attribute, String value) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * " +
                "from public.book where " + attribute + " = '" + value + "';");

        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        List<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs));
        return books;
    }

    public List<String> searchBookAuthors(String isbn) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select r.author_name " +
                "from public.book b join public.book_author r on b.isbn = r.book_isbn " +
                "where r.book_isbn = '" + isbn + "';");

        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        List<String> authors = new ArrayList<>();
        while (rs.next())
            authors.add(rs.getString(1));
        return authors;
    }

    public void changeUserProfile(String username, String attribute, String newValue) throws SQLException {
        if (attribute.toLowerCase().equals("role") || attribute.toLowerCase().equals("username"))
            return;
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update public.user u " +
                "set " + attribute + " = '" + newValue +
                "' where username = '" + username + "'; ");
    }
}