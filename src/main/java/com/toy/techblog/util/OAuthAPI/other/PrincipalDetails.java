package com.toy.techblog.util.OAuthAPI.other;

import com.toy.techblog.domain.user.entity.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@Slf4j
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Map<String, Object> attributes;
    private User user;
    private String accessToken;

    public PrincipalDetails(User user , Map<String, Object> attributes, String accessToken){
        this.user = user;
        this.attributes = attributes;
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getAccountType();
            }
        });
        return collections;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        log.info(user.getEmail());
        log.info(this.user.toString());
        return user.getEmail();
    }

    // 계정이 만료 되었는지 (true: 만료X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 (true: 만료X)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
