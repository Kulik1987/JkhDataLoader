package ru.kulikovskiy.jkh.jkhdataloader.service;

import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kulikovskiy.jkh.jkhdataloader.config.JkhGosuslugiConfig;
import ru.kulikovskiy.jkh.jkhdataloader.dao.HouseRepository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.House;
import ru.kulikovskiy.jkh.jkhdataloader.model.HouseJkh;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;
import static ru.kulikovskiy.jkh.jkhdataloader.Constants.*;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final RestTemplate jkhResttemplate;
    private final JkhGosuslugiConfig jkhGosuslugiConfig;
    private final OrganizationService organizationService;
    private final JmsTemplate jmsTemplate;

    public HouseService(HouseRepository houseRepository, RestTemplate jkhResttemplate, JkhGosuslugiConfig jkhGosuslugiConfig, OrganizationService organizationService, JmsTemplate jmsTemplate) {
        this.houseRepository = houseRepository;
        this.jkhResttemplate = jkhResttemplate;
        this.jkhGosuslugiConfig = jkhGosuslugiConfig;
        this.organizationService = organizationService;
        this.jmsTemplate = jmsTemplate;
    }

    private static final int itemPerPage = 1000;
    private static final int page = 1;
    private final String nameHouseMq = "JkhLoader.House";

    public void getHousesOfStreetJkh(String streetGuid, String streetCode) {
        ResponseEntity<HouseJkh[]> houseJkhResponse = getHousesStreet(streetCode);
        Arrays.stream(Objects.requireNonNull(houseJkhResponse.getBody())).forEach(houseJkh -> {
            Map<String, Object> houseMq = new HashMap();
            houseMq.put(HOUSE_GUID, houseJkh.getGuid());
            houseMq.put(HOUSE_CODE, houseJkh.getHouseGuid());
            houseMq.put(HOUSE_NUMBER, houseJkh.getHouseNumber());
            houseMq.put(POSTAL_CODE, houseJkh.getPostalCode());
            houseMq.put(BUILDING_NUMBER, houseJkh.getBuildingNumber());
            houseMq.put(STRUCT_NUMBER, houseJkh.getStructNumber());
            houseMq.put(ADDITIONAL_NAME, houseJkh.getAdditionalName());
            houseMq.put(HOUSE_CONDITION, houseJkh.getHouseCondition());
            houseMq.put(PROPERTY_STATE_GUID, houseJkh.getPropertyStateGuid());
            houseMq.put(FORMATTED_ADDRESS, houseJkh.getFormattedAddress());
            houseMq.put(STREET_GUID, streetGuid);
            houseMq.put(STREET_CODE, streetCode);

            jmsTemplate.convertAndSend(nameHouseMq, houseMq);
        });
    }

    @JmsListener(destination = nameHouseMq)
    @Transactional
    public void processingHouse(HashMap<String, Object> houseIn) {
        House house = Optional.of(houseRepository.findById((String) houseIn.get("houseGuid"))).get().orElse(new House());

        String streetGuid = ofNullable((String) houseIn.get(STREET_GUID)).orElse("");
        String houseGuid = ofNullable((String) houseIn.get(HOUSE_GUID)).orElse("");
        String houseCode = ofNullable((String) houseIn.get(HOUSE_CODE)).orElse("");

        house.setStreetGuid(streetGuid);
        house.setHouseGuid(houseGuid);
        house.setHouseCode(houseCode);
        ofNullable((String) houseIn.get(HOUSE_CODE)).ifPresent(house::setHouseCode);
        ofNullable((String) houseIn.get(HOUSE_NUMBER)).ifPresent(house::setHouseNumber);
        ofNullable((String) houseIn.get(POSTAL_CODE)).ifPresent(house::setHousePostalCode);
        ofNullable((String) houseIn.get(BUILDING_NUMBER)).ifPresent(house::setHouseBuildingNumber);
        ofNullable((String) houseIn.get(STRUCT_NUMBER)).ifPresent(house::setHouseStructNumber);
        ofNullable((String) houseIn.get(ADDITIONAL_NAME)).ifPresent(house::setHouseAdditionalName);
        ofNullable((String) houseIn.get(HOUSE_CONDITION)).ifPresent(house::setHouseCondition);
        ofNullable((String) houseIn.get(PROPERTY_STATE_GUID)).ifPresent(house::setHousePropertyStateGuid);
        ofNullable((String) houseIn.get(FORMATTED_ADDRESS)).ifPresent(house::setHouseFormattedAddress);
        houseRepository.save(house);

        organizationService.getOrganizationOfHouseJkh(streetGuid, houseGuid, houseCode);
    }

    private ResponseEntity<HouseJkh[]> getHousesStreet(String streetCode) {
        String url = UriComponentsBuilder.newInstance().uri(URI.create(jkhGosuslugiConfig.getUrl() + "/numbers"))
                .queryParam("actual", true).queryParam("itemPerPage", itemPerPage)
                .queryParam("page", page).queryParam("searchAggregatedAddresses", true).queryParam("streetCode", streetCode).toUriString();
        return jkhResttemplate.getForEntity(url, HouseJkh[].class);
    }

}
