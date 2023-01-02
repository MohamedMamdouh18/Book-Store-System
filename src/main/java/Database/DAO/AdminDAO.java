package Database.DAO;

import Database.DBConnector;
import Database.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO extends DAO {
    private static AdminDAO adminDAO = null;

    private AdminDAO() {
        this.connection = DBConnector.getAdminConnection();
    }

    public static AdminDAO getInstance() {
        if (adminDAO == null)
            AdminDAO.adminDAO = new AdminDAO();
        return AdminDAO.adminDAO;
    }

    public User signInUser(String username) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select *" +
                " from public.user u " +
                " where username = '" + username + "'; ");
        if (!resultSet.isBeforeFirst())
            return null;
        resultSet.next();
        return new User(resultSet);
    }

    public void signUpUser(User user) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("insert into public.user " +
                "values" + user.toString() + ";");
    }
}
