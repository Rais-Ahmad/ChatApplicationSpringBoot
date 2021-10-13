package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegesRepository  extends JpaRepository <Privileges, Long> {
    List<Privileges> findAllByStatus(boolean status);
//    List<Privileges> findAllByStatus(boolean status);
}