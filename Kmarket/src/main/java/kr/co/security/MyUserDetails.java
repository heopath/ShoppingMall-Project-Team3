package kr.co.security;


import kr.co.sboard.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class MyUserDetails implements UserDetails {
    /*
        UserDetails
         - 스프링 시큐리티 사용자 인증 객체
         - Security Context Holder(Session)에 저장되는 인증 객체
         - 로그인 성공 후 User 엔티티를 세션 보관
    */
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 목록 생성 후 반환해야 인증된 사용자의 권한 처리 가능
        // ROLE_ 접두어를 사용해야 hasRole(), hasAnyRole() 권한처리 메서드 사용가능
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        // 시큐리티에서 username은 사용자 id
        return user.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부(true: 만료안됨, false:만료됨)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부(true: 잠김안됨, false:잠김)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료여부(true: 잠김안됨, false:잠김)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부(true: 활성화, false:비활성화)
        return true;
    }
}