package Database.DAO;

import Database.DBConnector;
import Database.Models.Book;
import Database.Models.Order;

import java.sql.SQLException;
import java.sql.Statement;
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


}
