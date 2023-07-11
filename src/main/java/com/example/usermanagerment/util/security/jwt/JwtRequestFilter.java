package com.example.usermanagerment.util.security.jwt;

import com.example.usermanagerment.entity.User;
import com.example.usermanagerment.repository.UserRepository;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenBlacklistStore tokenBlacklistStore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
            if (token != null && !tokenBlacklistStore.isTokenInvalidated(token)) {
                try {
                    // mã hóa token
                    SignedJWT signedJWT = SignedJWT.parse(token);
                    JWTClaimsSet set = signedJWT.getJWTClaimsSet();
                    if (set.getExpirationTime().before(new Date())) {
                        System.out.println("Token has expired");
                        return;
                    }
                    String idUser = (String) set.getClaims().get("id");
                    setUpSpringAuthentication(idUser, token);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // lưu thông tin người login vào Holder
    public void setUpSpringAuthentication(String id, String token) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, token, authorities);
            System.out.println(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }
    }


}
