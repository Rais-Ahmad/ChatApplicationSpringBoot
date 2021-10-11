package com.chatapplicationspringBoot.Model;

import javax.persistence.*;
/**
 * @Author Rais Ahmad
 * @Date 10-11-2021
 * @Description Privileges POJO class
 */

@Entity
@Table(name = "t_privileges")
public class Privileges {

        @Id
        @Column(nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long privilegeId;
        @Column(nullable = false, unique = true)
        private String privilegeName;

        public Privileges(){

        }

    public long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}
