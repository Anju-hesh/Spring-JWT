package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.service;

import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.AuthDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.AuthResponseDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    AuthResponseDTO authenticate(AuthDTO authDTO);
}
