package Database.Models;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    private String username;
    private String book_isbn;
    private Date sale_date;
    private int count;

    @Override
    public String toString() {
        return "('" + this.book_isbn + "','" +
                this.username + "','" +
                this.count + "','" +
                this.sale_date + "'" +
                ")";
    }
}