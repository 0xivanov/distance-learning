package com.distance_learning.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @Column
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
