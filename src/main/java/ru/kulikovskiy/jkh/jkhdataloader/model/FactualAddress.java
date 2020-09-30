package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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

}
