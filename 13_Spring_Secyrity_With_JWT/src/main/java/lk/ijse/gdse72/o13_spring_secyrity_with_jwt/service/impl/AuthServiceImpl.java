package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.service.impl;

import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.AuthDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.AuthResponseDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.dto.RegisterDTO;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.entity.Role;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.entity.User;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.repository.UserRepository;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.service.AuthService;
import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        User user = userRepository.findByUsername(authDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + authDTO.getUsername()));

        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        String accessToken = jwtUtil.genarateToken(user.getUsername());
        return new AuthResponseDTO(accessToken);
    }

    @Override
    public String register(RegisterDTO registerDTO){

        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists with username: " + registerDTO.getUsername());
        }
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole().toUpperCase()))
                .build();
        userRepository.save(user);
        return "User Registration Successfull ...";
    }
}
