package Database.Models;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Order {
    private String book_isbn;
    private  String username;
    private Date order_date;
    private int count;

    public Order(String book_isbn, String username, Date order_date, int count) {
        this.book_isbn = book_isbn;
        this.username = username;
        this.order_date = order_date;
        this.count = count;
    }

    @Override
    public String toString() {
        return "('" + this.book_isbn + "', '" +
                this.username + "', '" +
                this.order_date + "', '" +
                this.count +
                "')";
    }
}
