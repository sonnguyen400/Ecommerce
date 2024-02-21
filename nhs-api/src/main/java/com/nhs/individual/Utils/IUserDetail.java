package com.nhs.individual.Utils;

import com.nhs.individual.Domain.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class IUserDetail implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> gran=new ArrayList<>();
        gran.add(new SimpleGrantedAuthority("ROLE_USER"));
        return gran;
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

    public static IUserDetail parseFrom(Account account){
        IUserDetail userDetail=new IUserDetail();
        userDetail.setUsername(account.getUsername());
        userDetail.setId(account.getId());
        userDetail.setPassword(account.getPassword());
        if(account.getRoles() != null&&account.getRoles().size()>0){
            userDetail.setAuthorities(account.getRoles().stream().map(e->new SimpleGrantedAuthority(e.getName())).toList());
        }
        return userDetail;
    }
}
