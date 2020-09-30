package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationJkhRequest {
    private String regionCode;
    private String cityCode;
    private String streetCode;
    private String houseCode;
}
