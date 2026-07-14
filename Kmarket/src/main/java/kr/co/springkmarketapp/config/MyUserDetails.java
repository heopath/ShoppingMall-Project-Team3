package kr.co.springkmarketapp.config;

import kr.co.springkmarketapp.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
public class MyUserDetails implements UserDetails, LoginUser  {

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority("ROLE_" + member.getRole())
        );
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        String status = member.getStatus();

        return "ACTIVE".equalsIgnoreCase(status)
                || "정상".equals(status);
    }
}