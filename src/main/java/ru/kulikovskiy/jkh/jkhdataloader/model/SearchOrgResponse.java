package ru.kulikovskiy.jkh.jkhdataloader.model;

import lombok.Data;
import ru.kulikovskiy.jkh.jkhdataloader.entity.Organization;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchOrgResponse {
    private List<OrganizationJkh> organizationSummaryWithNsiList = new ArrayList<>();
}
