package Database.Models;

import lombok.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sale {
    private String book_isbn;
    private  String username;
    private int count;
    private Date sale_date;

    public Sale(ResultSet set) throws SQLException {
        this.book_isbn = set.getString(1);
        this.username = set.getString(2);
        this.count = set.getInt(3);
        this.sale_date = set.getDate(4);

    }

    @Override
    public String toString() {
        return "('" + this.book_isbn + "', '" +
                this.username + "', '" +
                this.count + "', '" +
                this.sale_date +
                "')";
    }
}
