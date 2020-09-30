package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class OrganizationJkhRole {
    private String guid;
    private String createDate;
    private String lastEventDate;
    private Role role;
    private String status;
    private List<String> oktmoList;
    private List<String> fiasAddressList;
    private List<String> fiasHouseList;
    private List<String> attachments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationJkhRole)) return false;
        OrganizationJkhRole that = (OrganizationJkhRole) o;
        return Objects.equals(guid, that.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
