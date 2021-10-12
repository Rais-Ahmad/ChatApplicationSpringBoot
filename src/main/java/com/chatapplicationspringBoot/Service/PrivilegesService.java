package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Privileges;
import com.chatapplicationspringBoot.Repository.PrivilegesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrivilegesService {

    private final PrivilegesRepository privilegesRepository;

    public PrivilegesService(PrivilegesRepository privilegesRepository) {
        this.privilegesRepository = privilegesRepository;
    }


    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription List of privileges
     * @return
     */

    public List<Privileges> listAllPrivileges() {
        return privilegesRepository.findAll();
    }

    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription Get privilege by Id
     * @param id
     * @return
     */
    public Privileges getPrivileges(long id) {
        return privilegesRepository.findById(id).get();
    }

    /**
     * @Author Rais Ahmad
     * @Date 10-11-2021
     * @Discription Create a privilege
     * @param privileges
     */
    public void savePrivileges(Privileges privileges) {
        privilegesRepository.save(privileges);

    }

    public void updatePrivileges(Privileges privileges){

        privilegesRepository.save(privileges);
    }

    public void deletePrivileges(long id) {
        privilegesRepository.deleteById(id);
    }


}
