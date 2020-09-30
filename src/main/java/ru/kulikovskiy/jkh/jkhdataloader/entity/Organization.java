package ru.kulikovskiy.jkh.jkhdataloader.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    private String guid;
    private String address;
    private String cite;
    private String phone;
    private String shortName;
    private String fullName;
    private String orgResponse;
    private LocalDate createDt;
    private LocalDate updateDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(guid, that.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
