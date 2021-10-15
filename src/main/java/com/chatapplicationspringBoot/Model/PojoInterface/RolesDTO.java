package com.chatapplicationspringBoot.Model.PojoInterface;

import java.util.List;

public class RolesDTO {

    private String createdDate;
    private String name;
    private long id;
    private boolean Status;
    private String updatedDate;

    private List<PrivilegeDTO> permissions;
}
