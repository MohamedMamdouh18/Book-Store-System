package Database.Models;

import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor

public class Order {
    private int book_id;
    private String book_isbn;
    private String username;
    private Date order_date;
    private int count;

    public Order(int book_id, String book_isbn, String username, Date order_date, int count) {
        this.book_id = book_id;
        this.book_isbn = book_isbn;
        this.username = username;
        this.order_date = order_date;
        this.count = count;
    }

    @Override
    public String toString() {
        return "(default,  '" +
                this.username + "', '" + 
                this.book_isbn + "', '" +
                this.order_date + "', " +
                this.count +
                ")";
    }

}
