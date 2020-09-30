package ru.kulikovskiy.jkh.jkhdataloader.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HouseOrganizationIdentity implements Serializable {
    @NotNull
    private String houseGuid;
    @NotNull
    private String orgGuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseOrganizationIdentity that = (HouseOrganizationIdentity) o;
        return houseGuid.equals(that.houseGuid) &&
                orgGuid.equals(that.orgGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseGuid, orgGuid);
    }
}
