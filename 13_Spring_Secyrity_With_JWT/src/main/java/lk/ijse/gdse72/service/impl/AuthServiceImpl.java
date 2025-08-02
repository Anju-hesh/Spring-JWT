package lk.ijse.gdse72.service.impl;

import lk.ijse.gdse72.dto.AuthDTO;
import lk.ijse.gdse72.dto.AuthResponseDTO;
import lk.ijse.gdse72.dto.RegisterDTO;
import lk.ijse.gdse72.entity.Role;
import lk.ijse.gdse72.entity.User;
import lk.ijse.gdse72.repository.UserRepository;
import lk.ijse.gdse72.service.AuthService;
import lk.ijse.gdse72.util.JwtUtil;
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
