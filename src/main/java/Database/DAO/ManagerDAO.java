package Database.DAO;

import Database.DBConnector;
import Database.Models.Book;
import Database.Models.Order;
import Database.Models.Publisher;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.*;
import java.util.List;

public class ManagerDAO extends DAO {

    private static ManagerDAO managerDAO = null;

    private ManagerDAO() {
        this.connection = DBConnector.getManagerConnection();
    }

    public static ManagerDAO getInstance() {
        if (managerDAO == null)
            ManagerDAO.managerDAO = new ManagerDAO();
        return ManagerDAO.managerDAO;
    }

    public void addBook(Book book) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into public.book " +
                "values" + book.toString() + ";");
    }

    public void promoteCutomer(String userName) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update public.user u " +
                "set role = 'Manager' " +
                "where username = '" + userName + "'; ");
    }

    public void modifyBook(String attribute, String value, String isbn) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update public.book b " +
                "set " + attribute + " = '" + value +
                "' where isbn = '" + isbn + "'; ");
    }

    public void addAuthors(String isbn, List<String> authors) throws SQLException {
        Statement stmt = connection.createStatement();
        for (var author : authors) {
            System.out.println("values" + "(" + isbn + ", " + author + ")" + ";");
            stmt.execute("insert into public.book_author " +
                    "values" + "('" + isbn + "', '" + author + "')" + ";");
        }
    }

    public void addPublisher(Publisher publisher) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into public.publisher " +
                "values" + publisher.toString() + ";");
    }


    public void placeOrder(Order order) throws SQLException {
        System.out.println(order.toString());
        Statement stmt = connection.createStatement();
        stmt.execute("insert into public.order " +
                "values" + order.toString() + ";");
    }

    public void confirmOrder(Order order) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("delete from public.order " +
                " where book_isbn = '" + order.getBook_isbn() + "' and username = '" + order.getUsername() + "'; ");
    }

    public void reportLastMonthSales() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select * from public.sale where sale_date > '" + Date.valueOf(LocalDate.now().minusMonths(1)) + "'; ");
    }

    public Book getBookByISBN(String ISBN) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from public.book where isbn = '" + ISBN + "'; ");
        Book book = null;
        if (rs.next()) {
            book = Book.builder().isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .category(rs.getString("category"))
                    .publisher_name(rs.getString("publisher_name"))
                    .price(rs.getFloat("price"))
                    .minimum_stock(rs.getInt("minimum_stock"))
                    .stock(rs.getInt("stock"))
                    .publication_year(rs.getDate("publication_year")).build();
        }

        return book;
    }

    public Order getOrderByID(String ID) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from public.order where order_id = " + ID + ";");
        Order order = null;
        if(rs.next()) {
            order = Order.builder().book_id(rs.getInt("order_id"))
                                    .book_isbn(rs.getString("book_isbn"))
                                    .count(rs.getInt("count"))
                                    .username(rs.getString("username"))
                                    .order_date(rs.getDate("order_date"))
                                    .build();
        }

        return order;
    }

}
