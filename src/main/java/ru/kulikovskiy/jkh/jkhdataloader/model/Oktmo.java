package ru.kulikovskiy.jkh.jkhdataloader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
public class Oktmo {
    @JsonProperty("classType")
    private String classType;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rootEntityGuid")
    private String rootEntityGuid;
    @JsonProperty("actual")
    private String actual;
    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("controlNumber")
    private String controlNumber;
    @JsonProperty("additionalData")
    private String additionalData;
    @JsonProperty("description")
    private String description;
    @JsonProperty("parent")
    private String parent;
    @JsonProperty("status")
    private String status;
    @JsonProperty("level")
    private String level;
    @JsonProperty("hasChildren")
    private String hasChildren;
    @JsonProperty("hasActualChildren")
    private String hasActualChildren;
    @JsonProperty("loadAttribute")
    private String loadAttribute;
    @JsonProperty("oldMoscow")
    private String oldMoscow;
    @JsonProperty("utvDate")
    private String utvDate;
    @JsonProperty("vvedDate")
    private String vvedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oktmo)) return false;
        Oktmo oktmo = (Oktmo) o;
        return Objects.equals(guid, oktmo.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}



