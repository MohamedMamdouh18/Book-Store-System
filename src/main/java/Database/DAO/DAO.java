package Database.DAO;

import Database.Models.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO {
    Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public List<Book> searchBookByAttribute(String attribute, String value) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * " +
                "from public.book where " + attribute + " = '" + value + "';");

        List<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs));
        return books;
    }

    public List<Book> searchBookByAuthor(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * " +
                "from public.book b join public.book_author r on b.isbn = r.book_isbn " +
                "where r.author_name like '%" + name + "%';");

        List<Book> books = new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs));
        return books;
    }

    public void changeUserPassword(String userName, String newPassword) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update public.user u " +
                "set password = '" + newPassword +
                "' where username = '" + userName + "'; ");
    }

}
