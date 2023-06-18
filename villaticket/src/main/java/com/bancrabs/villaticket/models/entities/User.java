package com.bancrabs.villaticket.models.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "active", insertable = false)
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @Exclude
    private List<UserPrivilege> privileges;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @Exclude
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    @Exclude
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
    }

    @Override
    public boolean isAccountNonExpired() {
      return false;
    }

    @Override
    public boolean isAccountNonLocked() {
      return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return false;
    }

    @Override
    public boolean isEnabled() {
      return this.active;
    }

    public User(String unStr, String emStr, String pwStr) {
        this.username = unStr;
        this.password = pwStr;
        this.email = emStr;
    }
}
