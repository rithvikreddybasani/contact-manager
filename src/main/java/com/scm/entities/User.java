package com.scm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String userId;
    
    @Column(name = "user_name", nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Getter(AccessLevel.NONE)
    private String password;
    
    @Column(length = 1000)
    private String about;
    
    @Column(length = 1000)
    private String profilePic;
    
    private String phoneNumber;

    @Getter(value = AccessLevel.NONE)
    private boolean enabled = false;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    private String emailToken;

    // ==========================================
    // Spring Security UserDetails Overrides
    // ==========================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // ==========================================
    // Explicit Getter Functions for All Fields
    // ==========================================

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAbout() {
        return this.about;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean isEmailVerified() {
        return this.emailVerified;
    }

    public boolean isPhoneVerified() {
        return this.phoneVerified;
    }

    public Providers getProvider() {
        return this.provider;
    }

    public String getProviderUserId() {
        return this.providerUserId;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public List<String> getRoleList() {
        return this.roleList;
    }

    public String getEmailToken() {
        return this.emailToken;
    }

    // ==========================================
    // Explicit Setter Functions for All Fields
    // ==========================================

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }
}