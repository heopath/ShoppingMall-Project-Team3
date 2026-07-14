package kr.co.springkmarketapp.service.auth;

import kr.co.springkmarketapp.config.CustomOAuth2User;
import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        Boolean emailVerified = (Boolean) attributes.get("email_verified");
        if (!Boolean.TRUE.equals(emailVerified)) {
            throw new OAuth2AuthenticationException("이메일이 인증되지 않았습니다.");
        }


        Member member = memberRepository.<Member>findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .memberId("google_" + email)
                                .email(email)
                                .name(name)
                                .role("USER")
                                .provider("google")
                                .regDate(LocalDateTime.now())
                                .build()
                ));

        return new CustomOAuth2User(member, attributes);
    }
}