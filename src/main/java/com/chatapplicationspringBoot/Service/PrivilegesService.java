package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Privileges;
import com.chatapplicationspringBoot.Repository.PrivilegesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        privileges.setDate(dtf.format(now));

        privilegesRepository.save(privileges);

    }

    public void updatePrivileges(Privileges privileges){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        privileges.setUpdatedDate(dtf.format(now));
        privilegesRepository.save(privileges);
    }


    public void deletePrivileges(long id) {

        privilegesRepository.deleteById(id);
    }

    public ResponseEntity<Object> deletePrivilegebytID(long id){
        try {
            Optional<Privileges> privilege = privilegesRepository.findById(id);
            if(privilege.isPresent()){
                privilege.get().setStatus(true);
                privilegesRepository.saveAll(privilegesRepository.findAllById(Collections.singleton(id)));


                return new ResponseEntity("Deleted", HttpStatus.OK);
            }
            else return new ResponseEntity<>("ID does not Exist",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity("Database error",HttpStatus.NOT_FOUND);
        }
    }


}
