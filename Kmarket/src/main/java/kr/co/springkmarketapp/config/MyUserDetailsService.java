package kr.co.springkmarketapp.config;

import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        // 사용자 조회 후 시큐리티 인증 객체(UserDetails) 반환
        Optional<Member> optUser = memberRepository.findByMemberId(memberId);

        if (optUser.isPresent()){
            MyUserDetails details = MyUserDetails.builder()
                    .member(optUser.get())
                    .build();

            return details;
        }

        return null;
    }
}