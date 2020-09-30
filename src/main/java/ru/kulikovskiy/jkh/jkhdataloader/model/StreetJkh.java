package ru.kulikovskiy.jkh.jkhdataloader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@Data
public class StreetJkh {
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rootEntityGuid")
    private String rootEntityGuid;
    @JsonProperty("actual")
    private Boolean actual;
    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("aoGuid")
    private String aoGuid;
    @JsonProperty("aoLevel")
    private String aoLevel;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("formalName")
    private String formalName;
    @JsonProperty("offName")
    private String offName;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("parentGuid")
    private String parentGuid;
    @JsonProperty("regionCode")
    private String regionCode;
    @JsonProperty("autoCode")
    private String autoCode;
    @JsonProperty("areaCode")
    private String areaCode;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("ctarCode")
    private String ctarCode;
    @JsonProperty("placeCode")
    private String placeCode;
    @JsonProperty("planCode")
    private String planCode;
    @JsonProperty("streetCode")
    private String streetCode;
    @JsonProperty("extrCode")
    private String extrCode;
    @JsonProperty("sextCode")
    private String sextCode;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("isAddedManually")
    private Boolean isAddedManually;
    @JsonProperty("onApproval")
    private String onApproval;
    @JsonProperty("fiasAddrobjGuid")
    private String fiasAddrobjGuid;
    @JsonProperty("subjectCity")
    private String subjectCity;
    @JsonProperty("oktmo")
    private Oktmo oktmo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreetJkh)) return false;
        StreetJkh streetJkh = (StreetJkh) o;
        return Objects.equals(guid, streetJkh.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
