package tfg.apitfg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{
    private String documentNumber;
    private String phone;
    private String email;
    private String name;
    private String surname;
    private String cardNumber;
}
