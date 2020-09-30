package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class OrganizationJkh {
    private String guid;
    private String createDate;
    private String lastEventDate;
    private String fullName;
    private String shortName;
    private String chiefLastName;
    private String chiefFirstName;
    private String chiefMiddleName;
    private String orgAddress;
    private String phone;
    private String orgEmail;
    private String url;
    private String organizationType;
    private List<OrganizationJkhRole> organizationJkhRoles;
    private FactualAddress factualAddress;
    private String status;
    private String inn;
    private String kpp;
    private String ogrn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationJkh)) return false;
        OrganizationJkh that = (OrganizationJkh) o;
        return Objects.equals(guid, that.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
