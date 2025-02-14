package com.landingis.api.security;

import com.landingis.api.model.Group;
import com.landingis.api.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String username;
    private final String password;
    private final String group;
    private final Integer kind;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();

        Group userGroup = user.getGroup();
        this.group = userGroup.getName();
        this.kind = userGroup.getKind();

        this.authorities = userGroup.getPermissions().stream()
                .map(permission -> (GrantedAuthority) permission::getPcode)
                .collect(Collectors.toList());
    }

    public CustomUserDetails(Long userId, String username, String password, String group, Integer kind, List<String> pcodes) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.group = group;
        this.kind = kind;
        this.authorities = pcodes.stream()
                .map(permission -> (GrantedAuthority) () -> permission)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
