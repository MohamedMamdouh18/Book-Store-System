package Database.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Publisher {
    private String name;
    private String address;
    private String phone_number;

    public Publisher(String name, String address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "(default,  '" +
                this.name + "', '" + 
                this.address + "', '" +
                this.phone_number + "', " +
                ")";
    }
}
