package ru.kulikovskiy.jkh.jkhdataloader.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name= "street")
@NoArgsConstructor
public class Street {
    @Id
    @Column(name = "street_guid")
    private String streetGuid;
    @Column(name = "street_code")
    private String streetCode;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_response")
    private String streetResponse;
    @Column(name = "city_guid")
    private String cityGuid;
    @Column(name = "create_dt")
    private LocalDate createDate;
    @Column(name = "update_dt")
    private LocalDate updateDate;

    public Street(String streetGuid) {
        this.streetGuid = streetGuid;
    }
}
