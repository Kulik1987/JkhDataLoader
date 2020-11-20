package ru.kulikovskiy.jkh.jkhdataloader.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kulikovskiy.jkh.jkhdataloader.config.JkhLocation;
import ru.kulikovskiy.jkh.jkhdataloader.repository.HouseOrganizationRepository;
import ru.kulikovskiy.jkh.jkhdataloader.repository.OrganizationRepository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.HouseOrganization;
import ru.kulikovskiy.jkh.jkhdataloader.entity.HouseOrganizationIdentity;
import ru.kulikovskiy.jkh.jkhdataloader.entity.Organization;
import ru.kulikovskiy.jkh.jkhdataloader.model.OrganizationJkh;
import ru.kulikovskiy.jkh.jkhdataloader.model.OrganizationJkhRequest;
import ru.kulikovskiy.jkh.jkhdataloader.model.SearchOrgResponse;

import java.net.URI;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static ru.kulikovskiy.jkh.jkhdataloader.Constants.ITEM_PER_PAGE;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final RestTemplate jkhResttemplate;
    private final HouseOrganizationRepository houseOrganizationRepository;
    private final JkhLocation jkhLocation;

    public OrganizationService(OrganizationRepository organizationRepository,
                               RestTemplate jkhResttemplate,
                               HouseOrganizationRepository houseOrganizationRepository, JkhLocation jkhLocation) {
        this.organizationRepository = organizationRepository;
        this.jkhResttemplate = jkhResttemplate;
        this.houseOrganizationRepository = houseOrganizationRepository;
        this.jkhLocation = jkhLocation;
    }

    public void getOrganizationOfHouseJkh(String streetGuid, String houseGuid, String houseCode) {
        ResponseEntity<SearchOrgResponse> organizationJkhResponse = getOrganizationsHouse(streetGuid, houseCode);
        if (organizationJkhResponse.getBody().getOrganizationSummaryWithNsiList() != null) {
            organizationJkhResponse.getBody().getOrganizationSummaryWithNsiList().forEach(organizationJkh -> {
                saveOrganization(organizationJkh);
                saveOrganizationHouse(organizationJkh, houseGuid);
            });
        }
    }

    private ResponseEntity<SearchOrgResponse> getOrganizationsHouse(String streetCode, String houseCode) {
        OrganizationJkhRequest organizationJkhRequest = new OrganizationJkhRequest(jkhLocation.getRegionCode(), jkhLocation.getCityCode(), streetCode, houseCode);
        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        HttpEntity<OrganizationJkhRequest> request = new HttpEntity<>(organizationJkhRequest, headers);

        String url = UriComponentsBuilder.newInstance().uri(URI.create("https://dom.gosuslugi.ru/ppa/api/rest/services/ppa/public/organizations/searchByTerritory"))
                .queryParam("elementsPerPage", ITEM_PER_PAGE).queryParam("pageIndex", 1).toUriString();

        return jkhResttemplate.exchange(url, HttpMethod.POST, request, SearchOrgResponse.class);
    }

    private void saveOrganization(OrganizationJkh organizationJkh) {
        Organization organization = Optional.of(organizationRepository.findById(organizationJkh.getGuid())).get().orElse(new Organization());
        organization.setGuid(organizationJkh.getGuid());
        organization.setAddress(organizationJkh.getOrgAddress());
        organization.setCite(organizationJkh.getUrl());
        organization.setFullName(organizationJkh.getFullName());
        organization.setOrgResponse(organizationJkh.toString());
        organization.setPhone(organizationJkh.getPhone());
        organization.setShortName(organizationJkh.getShortName());
        organization.setInn(organizationJkh.getInn());
        organization.setKpp(organizationJkh.getKpp());
        organization.setOgrn(organizationJkh.getOgrn());
        organizationRepository.save(organization);
    }

    private void saveOrganizationHouse(OrganizationJkh organization, String houseGuid) {
        HouseOrganizationIdentity houseOrganizationIdentity = new HouseOrganizationIdentity(houseGuid, organization.getGuid());
        HouseOrganization houseOrganization = Optional.ofNullable(houseOrganizationRepository.findById(houseOrganizationIdentity)).get().orElse(new HouseOrganization(houseOrganizationIdentity));
        houseOrganizationRepository.save(houseOrganization);
    }
}