package Database.DAO;

import Database.DBConnector;
import Database.Models.Book;
import Database.Models.Order;
import Database.Models.Sale;
import Database.Models.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.*;
import java.util.ArrayList;
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

    public void promoteCustomer(String userName) throws SQLException {
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
        for(var author : authors){
            System.out.println("values" + "(" + isbn + ", " + author + ")" + ";");
            stmt.execute("insert into public.book_author " +
                    "values" + "('" + isbn + "', '" + author + "')" + ";");
        }
    }
    //TODO:Place order
    public void placeOrder(Order order) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into public.order " +
                        "values" + order.toString() + ";");
    }
    //TODO:Confirm Order
    public void confirmOrder(Order order) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("delete from public.order " +
                " where book_isbn = '" + order.getBook_isbn() + "' and username = '" + order.getUsername() + "'; ");
    }
    //TODO: Report total sales for all books in the previous month
    public ArrayList<Sale> reportLastMonthSales() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Sale> sales = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select * from public.sale where sale_date > '" + Date.valueOf(LocalDate.now().minusMonths(1)) + "'; ");
        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        while (rs.next())
            sales.add(new Sale(rs));
        return sales;
    }
    //TODO: Report top 10 books on selling for the last three months
    public ArrayList<Book> reportTopTenBooks() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Book> books = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select distinct isbn, title, publication_year, price, category, minimum_stock, stock, publisher_name" +
                                            " from public.sale s, public.book b where s.book_isbn = b.isbn and sale_date > '"
                                            + Date.valueOf(LocalDate.now().minusMonths(3)) + "' order by count desc limit 10; ");
        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        while (rs.next())
            books.add(new Book(rs));
        return books;
    }
    //TODO: Report top 5 customers with the most purchase amount for the last three months
    public ArrayList<User> reportTopFiveCustomers() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select distinct username, password, email_address, first_name, last_name, phone_number, address, role" +
                                            " from public.sale s, public.user u where s.username = u.username and sale_date > '"
                                            + Date.valueOf(LocalDate.now().minusMonths(3)) + "' group by username order by sum(count) desc limit 5; ");
        if (!rs.isBeforeFirst() )
            return new ArrayList<>();
        while (rs.next())
            users.add(new User(rs));
        return users;
    }

}
