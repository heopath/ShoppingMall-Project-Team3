package kr.co.springkmarketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보안 토큰 무효화 (개발 단계 및 Rest API 테스트를 위해 필수)
                .csrf(csrf -> csrf.disable())

                // 2. 어떤 요청이든 로그인 없이 모두 허용(permitAll)하겠다는 핵심 설정!
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )

                // 3. 시큐리티 기본 로그인 페이지와 폼 창을 비활성화합니다.
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}