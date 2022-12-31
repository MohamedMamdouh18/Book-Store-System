package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static Connection customerConnection = null;
    private static Connection managerConnection = null;
    private static Connection adminConnection = null;

    public static Connection getCustomerConnection() {
        if (customerConnection == null)
            customerConnection = connect_to_db(DBConstants.CustomerUser,
                    DBConstants.CustomerPass);
        return customerConnection;
    }

    public static Connection getManagerConnection() {
        if (managerConnection == null)
            managerConnection = connect_to_db(DBConstants.ManagerUser,
                    DBConstants.ManagerPass);
        return managerConnection;
    }

    public static Connection getAdminConnection() {
        if (adminConnection == null)
            adminConnection = connect_to_db(DBConstants.AdminUser,
                    DBConstants.AdminPass);
        return adminConnection;
    }


    private static Connection connect_to_db(String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + DBConstants.DatabaseURL, user, pass);
            if (conn != null)
                System.out.println("Connection Established");
            else
                System.out.println("Connection Failed");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
