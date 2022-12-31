package Database.DAO;

import Database.DBConnector;

public class CustomerDAO extends DAO {
    private static CustomerDAO customerDAO = null;

    private CustomerDAO() {
        this.connection = DBConnector.getCustomerConnection();
    }

    public static CustomerDAO getInstance() {
        if (customerDAO == null)
            CustomerDAO.customerDAO = new CustomerDAO();
        return CustomerDAO.customerDAO;
    }
}
