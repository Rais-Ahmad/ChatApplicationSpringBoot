package com.chatapplicationspringBoot.Model;

import javax.persistence.*;
/**
 * @Author Rais Ahmad
 * @Date 10-11-2021
 * @Description Privileges POJO class
 */

@Entity
@Table(name = "t_privileges")
//@SQLDelete(sql = "UPDATE t_privileges SET deleted = true WHERE id=?") // new
//@Where(clause = "deleted=false")  // new
public class Privileges {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long privilegeId;
        @Column(nullable = false, unique = true)
        private String privilegeName;
        @Column(nullable = false)
        private String date;
        @Column(nullable = true)
        private String updatedDate;

       // private boolean deleted = Boolean.FALSE;
       @Column(nullable = false)
       private boolean Status;


        public Privileges(){

        }

    public Privileges(long privilegeId, String privilegeName){
            this.privilegeId = privilegeId;
            this.privilegeName = privilegeName;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updateDate) {
        this.updatedDate = updateDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
