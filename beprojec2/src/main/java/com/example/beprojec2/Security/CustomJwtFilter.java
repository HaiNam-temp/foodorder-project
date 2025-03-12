package com.example.beprojec2.Security;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.util.JwtUtilHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtilHelper jwtUtilhelper;
    @Autowired
    private AccountReponsitory accountReponsitory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token=getTokenFromHeader(request); // gọi hàm lấy token từ header
        if(token!=null){
            if(jwtUtilhelper.verifyToken(token)){// sử dụng jwtutilhelper để kiểm tra token có hợp hệ
                String email=jwtUtilhelper.getUsername(token);
                Account account=accountReponsitory.findByUsername(email);
                if(account!=null){
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().getRolename()));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            email,
                            account.getPassword(),
                            authorities);
                    SecurityContext securityContext = SecurityContextHolder.getContext(); // tạo mới 1 chứng thực và quyền truy cập api cho user để spring sercurity cho phép truy cập
                    securityContext.setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token=null;
        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token=bearerToken.substring(7);
        }
        return token;
    }
}
