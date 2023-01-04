package Database.Models;

import lombok.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Cloneable{
    private String isbn;
    private String title;
    private Date publication_year;
    private Float price;
    private String category;
    private Integer minimum_stock;
    private Integer stock;
    private String publisher_name;

    public Book(ResultSet set) throws SQLException {
        this.isbn = set.getString("isbn");
        this.title = set.getString("title");
        this.publication_year = set.getDate("publication_year");
        this.price = set.getFloat("price");
        this.category = set.getString("category");
        this.minimum_stock = set.getInt("minimum_stock");
        this.stock = set.getInt("stock");
        this.publisher_name = set.getString("publisher_name");
    }

    @Override
    public String toString() {
        return "('" + this.isbn + "','" +
                this.title + "','" +
                this.publication_year + "'," +
                this.price + ",'" +
                this.category + "'," +
                this.minimum_stock + "," +
                this.stock + ",'" +
                this.publisher_name + "'" +
                ")";
    }
}
