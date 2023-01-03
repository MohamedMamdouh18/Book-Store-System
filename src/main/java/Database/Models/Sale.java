package Database.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Sale {
    private String book_isbn;
    private  String username;
    private int count;
    private Date sale_date;

    public Sale(String book_isbn, String username, int count, Date sale_date) {
        this.book_isbn = book_isbn;
        this.username = username;
        this.count = count;
        this.sale_date = sale_date;
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
