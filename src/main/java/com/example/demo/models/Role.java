package com.example.demo.models;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

// public enum Role {
//     USER(Collections.emptySet()),
//     MANAGER(Set.of(Permission.MANAGER_CREATE, Permission.MANAGER_READ, Permission.MANAGER_UPDATE, Permission.MANAGER_DELETE)),
//     ADMIN(Set.of(
//             Permission.ADMIN_READ, Permission.ADMIN_UPDATE, Permission.ADMIN_DELETE, Permission.ADMIN_CREATE,
//             Permission.MANAGER_CREATE, Permission.MANAGER_READ, Permission.MANAGER_UPDATE, Permission.MANAGER_DELETE));

//     private final Set<Permission> permissions;

//     Role(Set<Permission> permissions) {
//         this.permissions = permissions;
//     }

//     public Set<Permission> getPermissions() {
//         return permissions;
//     }

//     public List<SimpleGrantedAuthority> getAuthorities() {
//         List<SimpleGrantedAuthority> authorities = permissions.stream()
//             .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//             .collect(Collectors.toList());
//         authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name())); // Add role to authorities
//         return authorities;
//     }
// }

public enum Role {
    USER(Collections.emptySet()),
    MANAGER(Set.of(Permission.MANAGER_CREATE, Permission.MANAGER_READ, Permission.MANAGER_UPDATE, Permission.MANAGER_DELETE)),
    ADMIN(Set.of(
            Permission.ADMIN_READ, Permission.ADMIN_UPDATE, Permission.ADMIN_DELETE, Permission.ADMIN_CREATE,
            Permission.MANAGER_CREATE, Permission.MANAGER_READ, Permission.MANAGER_UPDATE, Permission.MANAGER_DELETE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions.stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name())); // Add role to authorities
        return authorities;
    }
}
