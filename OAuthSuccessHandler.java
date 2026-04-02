//package com.project.BTS.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import com.project.BTS.entity.User;
//import com.project.BTS.repository.UserRepository;
//
//import jakarta.servlet.http.*;
//
//@Component
//public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) {
//
//        OAuth2User user = (OAuth2User) authentication.getPrincipal();
//
//        String email = user.getAttribute("email");
//        String providerId = user.getName();
//
//        User existing = userRepository.findByEmail(email).orElse(null);
//
//        if (existing == null) {
//            User newUser = new User();
//            newUser.setEmail(email);
//            newUser.setAuthProvider("GOOGLE");
//            newUser.setProviderId(providerId);
//            newUser.setIsVerified(true);
//
//            userRepository.save(newUser);
//        }
//    }
//}