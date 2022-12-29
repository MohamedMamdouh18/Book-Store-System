package Database.DAO;

import Database.DBConnector;

public class CustomerDAO extends DAO {
    public CustomerDAO() {
        this.connection = DBConnector.getCustomerConnection();
    }
}
