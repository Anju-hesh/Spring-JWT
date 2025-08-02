package lk.ijse.gdse72.service;

import lk.ijse.gdse72.dto.AuthDTO;
import lk.ijse.gdse72.dto.AuthResponseDTO;
import lk.ijse.gdse72.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    AuthResponseDTO authenticate(AuthDTO authDTO);
}
