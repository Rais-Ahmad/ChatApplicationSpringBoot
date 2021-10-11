package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Role;
import com.chatapplicationspringBoot.Service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger LOG = LogManager.getLogger(RoleController.class);
    private String key = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";
    private String userKey = "abc123";

    public boolean authorization(String checkKey) {
        if (checkKey.equals(key)) {
            return true;
        } else return false;
    }

    public boolean authorizationUser(String checkKey) {
        if (checkKey.equals(userKey)) {
            return true;
        } else return false;
    }
    final

    RoleService roleService;

    public RoleController(RoleService roleService) {
         this.roleService = roleService;
    }

    @GetMapping("/allroles")
    public ResponseEntity<Object> roleList(@RequestHeader("Authorization") String authValue) {

        if (authorization(authValue) || authorizationUser(authValue)) {
            List<Role> roleList = roleService.listAllRoles();

            if (roleList.isEmpty()) {
                LOG.info("No data available");
                return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
            } else {
                LOG.info("List of roles : " + roleList +" ");
                return new ResponseEntity<>(roleList, HttpStatus.OK);
            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add Role
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestHeader("Authorization") String authValue, @RequestBody Role role) {

        if (authorization(authValue)) {
            roleService.saveRole(role);
            LOG.info("Role : " + role +" added successfully ");
            return new ResponseEntity<>("Role added successfully", HttpStatus.CREATED);

        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Update Role
     * @param role
     * @return
     */
    @PutMapping("/updateRole")
    public ResponseEntity<Object> updateRole(@RequestHeader("Authorization") String authValue, @RequestBody Role role) {

        if (authorization(authValue)) {
            try {
                roleService.updateRole(role);
                LOG.info("Role updated successfully:  " + role);
                return new ResponseEntity<>("Role updated successfully ", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Role not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Delete Category
     * @param id
     * @return
     */
    @DeleteMapping("/deleteRole/{id}")

    public ResponseEntity<String> deleteRole(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {

        if (authorization(authValue)) {
            try {
                roleService.deleteRole(id);
                LOG.info("Role: " + id + " deleted successfully!");
                return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
            }

        } else{
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }

    }


}
