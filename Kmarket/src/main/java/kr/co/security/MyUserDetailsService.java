package kr.co.security;

import kr.co.sboard.entity.User;
import kr.co.sboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /*
        - UserDetailsService 호출하는 AuthenticationProvider에서 username(사용자아이디)을 전달
        - AuthenticationProvider에서 실제 사용자 아이디, 비밀번호 인증을 수행
        - 인증 성공 후 loadUserByUsername메서드의 매개변수 username 으로 인증된 사용자 아이디 전달
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 사용자 조회 후 시큐리티 인증 객체(UserDetails) 반환
        Optional<User> optUser = userRepository.findById(username);

        if (optUser.isPresent()){
            // 💡 [수정] optUser.get()을 직접 활용하여 콘솔에 데이터를 출력합니다.
            System.out.println("====== 🔍 시큐리티 최종 디버깅 ======");
            System.out.println("DB에서 조회한 아이디: " + optUser.get().getUserid());
            System.out.println("DB에서 조회한 암호화 비번: " + optUser.get().getPass());
            System.out.println("DB에서 조회한 권한(Role): " + optUser.get().getRole());
            System.out.println("====================================");

            MyUserDetails details = MyUserDetails.builder()
                    .user(optUser.get())
                    .build();

            return details;
        }
        throw new UsernameNotFoundException("존재하지 않는 사용자 아이디입니다: " + username);
    }
}