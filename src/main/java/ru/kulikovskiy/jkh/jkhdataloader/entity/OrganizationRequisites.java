package ru.kulikovskiy.jkh.jkhdataloader.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "organization_requisites")
public class OrganizationRequisites {
    @Id
    private String guid;
    private String inn;
    private String kpp;
    private String ogrn;
    private String orgResponse;
    private LocalDate createDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationRequisites)) return false;
        OrganizationRequisites that = (OrganizationRequisites) o;
        return Objects.equals(guid, that.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }

    private LocalDate updateDt;
}
