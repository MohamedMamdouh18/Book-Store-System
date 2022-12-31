package Database.Models;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String email_address;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String address;
    private String role;

    public User(ResultSet set) throws SQLException {
        this.username = set.getString(1);
        this.password = set.getString(2);
        this.email_address = set.getString(3);
        this.first_name = set.getString(4);
        this.last_name = set.getString(5);
        this.phone_number = set.getString(6);
        this.address = set.getString(7);
        this.role = set.getString(8);
    }

    @Override
    public String toString() {
        return "('" + this.username + "','" +
                this.password + "','" +
                this.email_address + "','" +
                this.first_name + "','" +
                this.last_name + "','" +
                this.phone_number + "','" +
                this.address + "','" +
                this.role + "'" +
                ")";
    }
}
