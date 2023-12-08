package org.system.onlineelection.application.service;


import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.system.onlineelection.infrastructure.adapter.entity.ERole;
import org.system.onlineelection.infrastructure.adapter.entity.PersonEntity;
import org.system.onlineelection.infrastructure.adapter.entity.RoleEntity;
import org.system.onlineelection.infrastructure.adapter.jwt.JwtUtils;
import org.system.onlineelection.infrastructure.adapter.payload.JwtResponse;
import org.system.onlineelection.infrastructure.adapter.payload.LoginRequest;
import org.system.onlineelection.infrastructure.adapter.payload.RegisterRequest;
import org.system.onlineelection.infrastructure.mapper.AuthResponse;
import org.system.onlineelection.infrastructure.repository.PersonRepository;
import org.system.onlineelection.infrastructure.repository.RoleRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private final AuthenticationManager authenticationManager;


    @Autowired
    private final RoleRepository roleRepository ;

    @Autowired
    private final PersonRepository userRepository;

    public JwtResponse login(@NotNull LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",roles);
        claims.put("id_person", userDetails.getId());
        String jwt = jwtUtils.generateJwtToken(authentication,claims);

        return new JwtResponse(jwt);
    }


    public AuthResponse register(@NotNull RegisterRequest request) {

        Set<String> strRoles = request.getRoles();
        Set<RoleEntity> roles = request.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .nameRole(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
        String pass = passwordEncoder.encode(request.getPassword());

        PersonEntity user = PersonEntity.builder()
                .name(request.getName())
                .userName(request.getUserName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode( request.getPassword()))
                .roles(roles)
                .build();


        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        return AuthResponse.builder()
                .token(jwtUtils.generateJwtToken(authentication))
                .build();
    }


}