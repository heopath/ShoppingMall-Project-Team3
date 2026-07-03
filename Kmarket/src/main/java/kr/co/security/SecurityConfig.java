package kr.co.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    스프링 시큐리티 설정 클래스
     - 인증 및 인가 처리 설정
     - 기타 시큐리티 설정
*/
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 로그인 설정
        httpSecurity.formLogin( form -> form
                .loginPage("/user/login")
                .defaultSuccessUrl("/article/list")
                .failureUrl("/user/login?login=fail")
                .usernameParameter("userid")
                .passwordParameter("pass")
        );

        // 로그아웃 설정
        httpSecurity.logout( config -> config
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/user/login?logout=success")
        );

        // 인가 설정
        httpSecurity.authorizeHttpRequests( authorize -> authorize
                .requestMatchers("/").permitAll() // 루트(/) 경로는 인증없이 모든 요청 허용
                .requestMatchers("/article/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
                //.requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                //.requestMatchers("/member/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
                .anyRequest().permitAll()
        );

        // 기타 보안 설정
        httpSecurity.csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 시큐리티에서 사용하는 비밀번호 생성기
        return new BCryptPasswordEncoder();
    }
}








