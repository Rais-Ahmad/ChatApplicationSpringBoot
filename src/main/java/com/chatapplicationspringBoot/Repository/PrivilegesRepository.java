package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository  extends JpaRepository <Privileges, Long> {

}