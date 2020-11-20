package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class HouseJkh {
    private String guid;
    private String code;
    private String rootEntityGuid;
    private Boolean actual;
    private String lastUpdateDate;
    private String createDate;
    private String houseGuid;
    private String aoGuid;
    private String postalCode;
    private String houseNumber;
    private String buildingNumber;
    private String structNumber;
    private String additionalName;
    private String houseCondition;
    private String propertyStateGuid;
    private String formattedAddress;
    private Boolean isAddedManually;
    private String onApproval;
    private String estStatus;
    private String strStatus;
    private String fiasHouseGuid;
    private Boolean aggregated;
    private String childAddresses;
    private String parentAggregatedGuid;
    private String houseTextAddress;
    private List<String> doubles;
    private String actualHouseGuid;
    private Oktmo oktmo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseJkh)) return false;
        HouseJkh houseJkh = (HouseJkh) o;
        return Objects.equals(guid, houseJkh.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
