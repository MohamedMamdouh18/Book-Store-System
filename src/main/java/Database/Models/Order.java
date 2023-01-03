package Database.Models;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Order {
    private String book_isbn;
    private  String username;
    private Date order_date;
    private int count;

    @Override
    public String toString() {
        return "('" + this.book_isbn + "', '" +
                this.username + "', '" +
                this.order_date + "', '" +
                this.count +
                "')";
    }
}
