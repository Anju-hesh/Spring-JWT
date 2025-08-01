package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.controller;

import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.ApiResponse;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.AuthDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.RegisterDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.register(registerDTO)

        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.authenticate(authDTO)
        ));
    }
}
