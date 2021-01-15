package com.example.lly.entity.RBAC;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity   //This entity will be administered by JPA
@Table(name = "rbac_permission")
public class Permission implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //由数据库自行修改ID
    private Long id;

    private String name;

    @Column(columnDefinition = "enum('menu', 'button')")
    private String resourceType;    //资源类型

    private String url;

    private String permission;

    private Long parentId;      //父类编号

    private Boolean available = Boolean.FALSE;

    @Transient
    private List<Permission> permissions;   //不会被持久化

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
               joinColumns = {@JoinColumn(name = "permission_id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
