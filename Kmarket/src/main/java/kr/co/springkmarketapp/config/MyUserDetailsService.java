package kr.co.springkmarketapp.config;

import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId)
            throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("존재하지 않는 아이디입니다.")
                );

        return MyUserDetails.builder()
                .member(member)
                .build();
    }
}