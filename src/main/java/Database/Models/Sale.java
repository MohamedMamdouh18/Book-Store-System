package Database.Models;

import lombok.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sale {
    private Integer sale_id;
    private String book_isbn;
    private String username;
    private Integer count;
    private Date sale_date;

    public Sale(int sale_id, String book_isbn, String username, Date sale_date, int count) {
        this.sale_id = sale_id;
        this.book_isbn = book_isbn;
        this.username = username;
        this.count = count;
        this.sale_date = sale_date;
    }

    public Sale(ResultSet set) throws SQLException {
        this.book_isbn = set.getString("book_isbn");
        this.username = set.getString("username");
        this.count = set.getInt("count");
        this.sale_date = set.getDate("sale_date");
        this.sale_id = set.getInt("sale_id");
    }
    @Override
    public String toString() {
        return "(default,  '" +
                this.username + "', '" + 
                this.book_isbn + "', '" +
                this.sale_date + "', " +
                this.count +
                ")";
    }
}

