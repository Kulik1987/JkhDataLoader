package ru.kulikovskiy.jkh.jkhdataloader.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "house")
public class House {
    @Id
    @Column(name = "house_guid")
    private String houseGuid;
    @Column(name = "street_guid")
    private String streetGuid;
    @Column(name = "house_code")
    private String houseCode;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "house_postal_code")
    private String housePostalCode;
    @Column(name = "house_building_number")
    private String houseBuildingNumber;
    @Column(name = "house_struct_number")
    private String houseStructNumber;
    @Column(name = "house_additional_name")
    private String houseAdditionalName;
    @Column(name = "house_condition")
    private String houseCondition;
    @Column(name = "house_property_state_guid")
    private String housePropertyStateGuid;
    @Column(name = "house_formatted_address")
    private String houseFormattedAddress;
    @Column(name = "house_response")
    private String houseResponse;
    @Column(name = "create_dt")
    private Date createDate;
    @Column(name = "update_dt")
    private Date updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return houseGuid.equals(house.houseGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseGuid);
    }
}
