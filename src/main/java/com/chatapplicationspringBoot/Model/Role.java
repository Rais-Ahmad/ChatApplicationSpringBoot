package com.chatapplicationspringBoot.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Rais Ahmad
 * @Date 10-11-2021
 * @Description Role POJO class
 */

@Entity
@Table(name = "t_Role")
public class Role {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    @Column(nullable = false, unique = true)
    private String roleName;
    @Column(nullable = true)
    private String date;
    @Column(nullable = true)
    private String updateDate;
    @ManyToMany(targetEntity = Privileges.class, cascade = {
            CascadeType.MERGE
    })

    @JoinTable(
            name = "t_RolePrivileges",
            joinColumns = {
                    @JoinColumn(name = "roleId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "privilegeId")
            }
    )

    Set<Privileges> privileges = new HashSet<Privileges>();


    public Role(){

    }

    public Role(long roleId, String roleName){
    this.roleId = roleId;
    this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Privileges> getPrivileges() {
        return privileges;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setPrivileges(Set<Privileges> privileges) {
        this.privileges = privileges;
    }
}
