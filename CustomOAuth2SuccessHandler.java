package com.project.BTS.config;

import com.project.BTS.entity.User;
import com.project.BTS.repository.UserRepository;
import com.project.BTS.service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

       
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.builder()
                    .email(email)
                    .name(name)
                    .password("GOOGLE") // dummy
                    .authProvider("google")
                    .isVerified(true)
                    .build();

            userRepository.save(user);
        }
//        String token = jwtService.generateToken(email);
//
//        response.sendRedirect(
//            "http://localhost:5500/index.html?token=" + token
//        );
        response.sendRedirect("http://localhost:5500/index.html?email=" + email+"&name="+name);
    }
}