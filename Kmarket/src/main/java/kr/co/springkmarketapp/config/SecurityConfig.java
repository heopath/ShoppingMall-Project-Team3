package kr.co.springkmarketapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                /*
                 * 현재 개발 단계 설정
                 * Ajax / REST 요청 테스트 중이므로 우선 비활성화
                 * 나중에 결제, 주문 등 완성 단계에서 활성화 예정
                 */
                .csrf(csrf -> csrf.disable())

                .httpBasic(basic -> basic.disable())

                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(authorize -> authorize

                        // 쿠폰 목록/등록 화면은 관리자와 판매자가 함께 사용한다.
                        .requestMatchers(HttpMethod.GET, "/admin/coupon/list")
                        .hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers(HttpMethod.POST, "/admin/coupon/register")
                        .hasAnyRole("ADMIN", "SELLER")

                        /*
                         * 로그인 없이 접근 가능한 주소
                         */
                        .requestMatchers(
                                "/",
                                "/member/login",
                                "/member/register",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/uploads/**",
                                "/error"
                        ).permitAll()

                        /*
                         * 관리자 전용
                         */
                        .requestMatchers("/admin", "/admin/**")
                        .hasRole("ADMIN")

                        /*
                         * 판매자 전용
                         */
                        .requestMatchers("/seller", "/seller/**")
                        .hasRole("SELLER")

                        /*
                         * 로그인 회원 공통
                         * USER / SELLER / ADMIN 모두 접근 가능
                         */
                        .requestMatchers(
                                "/my/**",

                                "/product/cart",
                                "/product/cart/**",

                                "/product/order",
                                "/product/order/**",

                                "/product/complete",
                                "/product/complete/**"
                        ).authenticated()

                        /*
                         * 아직 권한 설정 전인 나머지 주소는 공개
                         */
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form

                        /*
                         * GET /member/login
                         */
                        .loginPage("/member/login")

                        /*
                         * POST /member/login
                         * Spring Security가 직접 로그인 처리
                         */
                        .loginProcessingUrl("/member/login")

                        .usernameParameter("memberId")
                        .passwordParameter("password")

                        .defaultSuccessUrl("/", true)

                        .failureUrl("/member/login?login=fail")

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutUrl("/member/logout")

                        .logoutSuccessUrl("/member/login?logout=success")

                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")

                        .permitAll()
                )

                /*
                 * 로그인 성공 시 기존 세션 ID를 변경한다.
                 */
                .sessionManagement(session -> session
                        .sessionFixation()
                        .changeSessionId()
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
