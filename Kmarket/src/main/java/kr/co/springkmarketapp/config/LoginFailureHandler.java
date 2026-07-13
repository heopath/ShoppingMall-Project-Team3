package kr.co.springkmarketapp.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMsg = "fail"; // 기본값

        // 실제 발생한 예외를 가져옴 (감싸진 예외 안의 원인 확인)
        Throwable cause = exception.getCause();

        if (cause instanceof DisabledException) {
            errorMsg = "leave";
        }
        // 직접 발생시킨 예외가 바로 들어오는 경우도 대비
        else if (exception instanceof DisabledException) {
            errorMsg = "leave";
        }

        setDefaultFailureUrl("/member/login?error=" + errorMsg);
        super.onAuthenticationFailure(request, response, exception);
    }
}