package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDTO {
    private String username;
    private String password;
    private String role; // "ADMIN" or "USER"
}
