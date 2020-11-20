package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class FactualAddress {
    private String region;
    private String area;
    private String city;
    private String settlement;
    private String planningStructureElement;
    private String street;
    private String house;
    private String additionalInfo;
    private String houseNumber;
    private String buildingNumber;
    private String structNumber;
    private String formattedAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FactualAddress)) return false;
        FactualAddress that = (FactualAddress) o;
        return Objects.equals(region, that.region) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(house, that.house) &&
                Objects.equals(houseNumber, that.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, city, street, house, houseNumber);
    }
}
