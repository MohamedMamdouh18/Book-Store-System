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
        this.username = set.getString("username");
        this.password = set.getString("password");
        this.email_address = set.getString("email_address");
        this.first_name = set.getString("first_name");
        this.last_name = set.getString("last_name");
        this.phone_number = set.getString("phone_number");
        this.address = set.getString("address");
        this.role = set.getString("role");
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
