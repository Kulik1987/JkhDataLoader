package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Role {
    private String guid;
    private String code;
    private String rootEntityGuid;
    private String actual;
    private String lastUpdateDate;
    private String createDate;
    private String organizationRoleName;
    private String shortName;
    private String citizenRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(guid, role.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
