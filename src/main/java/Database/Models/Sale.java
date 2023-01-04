package Database.Models;

import lombok.*;
import java.sql.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sale {
    private int sale_id;
    private String book_isbn;
    private String username;
    private int count;
    private Date sale_date;

    public Sale(int sale_id, String book_isbn, String username, Date sale_date, int count) {
        this.sale_id = sale_id;
        this.book_isbn = book_isbn;
        this.username = username;
        this.count = count;
        this.sale_date = sale_date;
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

