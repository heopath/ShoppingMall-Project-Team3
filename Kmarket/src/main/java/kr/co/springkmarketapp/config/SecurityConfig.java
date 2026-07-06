package kr.co.springkmarketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보안 토큰 무효화 (개발 단계 및 Rest API 테스트를 위해 필수)
                .csrf(csrf -> csrf.disable())

                // 시큐리티 기본 로그인 페이지와 폼 창을 비활성화합니다.
                .httpBasic(basic -> basic.disable())

                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/member/login?login=fail")
                        .usernameParameter("memberId")
                        .passwordParameter("password")
                )

                // 로그아웃 설정
                .logout(config -> config
                        .logoutUrl("/member/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/member/login?logout=success")
                )

                // 인가 설정
                .authorizeHttpRequests(authorize -> authorize
                        // ===== 개발 중일 때 =====
                        // 모든 페이지 접근 허용 (어떤 요청이든 로그인 없이 모두 허용(permitAll)하겠다는 핵심 설정!)
                        //.anyRequest().permitAll()

                        // ===== 프로젝트 완료되었을 때 =====
                        // 아래 주석을 해제하고 위 anyRequest().permitAll()은 없애기
                .requestMatchers(
                        "/",
                        "/member/login",
                        "/member/register",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

//                .requestMatchers("/my/**").authenticated()   //  로그인해야 접근 가능
//                .requestMatchers("/cart/**").authenticated()
//                .requestMatchers("/order/**").authenticated()
//                .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
                .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }
}