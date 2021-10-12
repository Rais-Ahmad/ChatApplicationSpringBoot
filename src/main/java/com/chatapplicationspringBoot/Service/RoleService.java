package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Role;
import com.chatapplicationspringBoot.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription List of Roles
     * @return
     */

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription Get Role by Id
     * @param id
     * @return
     */
    public Role getRole(long id) {
        return roleRepository.findById(id).get();
    }

    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription Create a Role
     * @param role
     */
    public void saveRole(Role role) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        role.setDate(dtf.format(now));
        roleRepository.save(role);

    }

    public void updateRole(Role role){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        role.setUpdateDate(dtf.format(now));
        roleRepository.save(role);
    }

    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

}
