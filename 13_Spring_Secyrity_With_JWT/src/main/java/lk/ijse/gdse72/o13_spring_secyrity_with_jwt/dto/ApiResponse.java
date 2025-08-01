package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private int code;
    private String status;
    private Object data;
}
