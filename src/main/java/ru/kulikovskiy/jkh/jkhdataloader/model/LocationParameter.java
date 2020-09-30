package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;

@Data
public class LocationParameter {
    private String regionCode;
    private String cityCode;
    private String streetCode;
    private String streetGuid;
    private String houseCode;
    private String houseGuid;
}
