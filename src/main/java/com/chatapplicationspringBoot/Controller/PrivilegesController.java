package com.chatapplicationspringBoot.Controller;


import com.chatapplicationspringBoot.Model.Privileges;
import com.chatapplicationspringBoot.Service.PrivilegesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/privileges")
public class PrivilegesController {
    private static final Logger LOG = LogManager.getLogger(PrivilegesController.class);
    private String key = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";

    public boolean authorization(String checkKey) {
        if (checkKey.equals(key)) {
            return true;
        } else return false;
    }

    final

    PrivilegesService privilegesService;

    public PrivilegesController(PrivilegesService privilegesService) {
        this.privilegesService = privilegesService;
    }


    @GetMapping("/allPrivileges")
    public ResponseEntity<Object> privilegesList(@RequestHeader("Authorization") String authValue) {

        if (authorization(authValue)) {
            List<Privileges> privilegesList = privilegesService.listAllPrivileges();

            if (privilegesList.isEmpty()) {
                LOG.info("No data available");
                return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
            } else {
                LOG.info("List of privileges : " + privilegesList +" ");
                return new ResponseEntity<>(privilegesList, HttpStatus.OK);
            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add Privilege
     * @param privileges
     * @return
     */
    @PostMapping("/addPrivilege")
    public ResponseEntity<String> addPrivilege(@RequestHeader("Authorization") String authValue, @RequestBody Privileges privileges) {

        if (authorization(authValue)) {
            privilegesService.savePrivileges(privileges);
            LOG.info("Privilege : " + privileges +" added successfully ");
            return new ResponseEntity<>("Privilege added successfully", HttpStatus.CREATED);

        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Update privilege
     * @param privileges
     * @return
     */
    @PutMapping("/updatePrivileges")
    public ResponseEntity<Object> updatePrivilege(@RequestHeader("Authorization") String authValue, @RequestBody Privileges privileges) {

        if (authorization(authValue)) {
            try {
                privilegesService.updatePrivileges(privileges);
                LOG.info("Privilege updated successfully:  " + privileges);
                return new ResponseEntity<>("privilege updated successfully ", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("privilage not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Delete privilege
     * @param id
     * @return
     */
    @DeleteMapping("/deletePrivilege/{id}")

    public ResponseEntity<String> deletePrivilege(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {

        if (authorization(authValue)) {
            try {
                privilegesService.deletePrivileges(id);
                LOG.info("Privilege: " + id + " deleted successfully!");
                return new ResponseEntity<>("Privilege deleted successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Privilege not found", HttpStatus.NOT_FOUND);
            }

        } else{
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }

    }


}
