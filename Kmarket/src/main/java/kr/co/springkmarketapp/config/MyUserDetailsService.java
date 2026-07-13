package kr.co.springkmarketapp.config;

import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
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

        // 1. 회원 조회
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("존재하지 않는 아이디입니다.")
                );

        // 2. 탈퇴 회원 체크 (status가 'leave'인 경우)
        if ("leave".equals(member.getStatus())) {
            throw new DisabledException("탈퇴한 회원입니다.");
        }

        // 3. 정상 회원 객체 반환
        return MyUserDetails.builder()
                .member(member)
                .build();
    }
}