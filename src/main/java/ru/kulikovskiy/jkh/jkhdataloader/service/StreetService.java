package ru.kulikovskiy.jkh.jkhdataloader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kulikovskiy.jkh.jkhdataloader.config.JkhGosuslugiConfig;
import ru.kulikovskiy.jkh.jkhdataloader.config.JkhLocation;
import ru.kulikovskiy.jkh.jkhdataloader.dao.StreetRerository;
import ru.kulikovskiy.jkh.jkhdataloader.entity.Street;
import ru.kulikovskiy.jkh.jkhdataloader.model.JkhDataLoaderAllResponse;
import ru.kulikovskiy.jkh.jkhdataloader.model.LocationParameter;
import ru.kulikovskiy.jkh.jkhdataloader.model.StreetJkh;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;
import static ru.kulikovskiy.jkh.jkhdataloader.Constants.*;

@Service
@Slf4j
public class StreetService {

    private final StreetRerository streetRepository;
    private final JkhGosuslugiConfig jkhGosuslugiConfig;
    private final HouseService houseService;
    private final RestTemplate jkhResttemplate;
    private final JmsTemplate jmsTemplate;
    private final JkhLocation jkhLocation;

    private final String nameStreetMq = "JkhDataLoader.Street";

    public StreetService(StreetRerository streetRepository, JkhGosuslugiConfig jkhGosuslugiConfig, HouseService houseService, RestTemplate jkhResttemplate, JmsTemplate jmsTemplate, JkhLocation jkhLocation) {
        this.streetRepository = streetRepository;
        this.jkhGosuslugiConfig = jkhGosuslugiConfig;
        this.houseService = houseService;
        this.jkhResttemplate = jkhResttemplate;
        this.jmsTemplate = jmsTemplate;
        this.jkhLocation = jkhLocation;
    }

    public JkhDataLoaderAllResponse getAllStreetsJkh() {
        int maxPageKhimki = 102;
        for (int i = 1; i < maxPageKhimki; i++) {
            int itemPerPage = 10;
            getStreetsJkhAndSendToMq(itemPerPage, i);
        }

        JkhDataLoaderAllResponse response = new JkhDataLoaderAllResponse();
        response.setStatus("ОК");
        response.setDescription("В БД загружены улицы г.Химки");
        return response;

    }

    private void getStreetsJkhAndSendToMq(int itemPerPage, int page) {
        ResponseEntity<StreetJkh[]> streetJkhResponse = getStreetsPackJkh(itemPerPage, page);

        Arrays.stream(Objects.requireNonNull(streetJkhResponse.getBody())).forEach(street -> {
            Map<String, Object> streetMq = new HashMap();
            streetMq.put(STREET_GUID, street.getGuid());
            streetMq.put(STREET_CITY_CODE, street.getCityCode());
            streetMq.put(STREET_STREET_CODE, street.getStreetCode());
            streetMq.put(STREET_FORMAL_NAME, street.getFormalName());
            streetMq.put(STREET_ALL_INFO, street.toString());
            streetMq.put(REGION_CODE, jkhLocation.getRegionCode());
            streetMq.put(CITY_CODE, jkhLocation.getCityCode());
            streetMq.put(STREET_CODE, street.getAoGuid());


            jmsTemplate.convertAndSend(nameStreetMq, streetMq);
        });
    }

    @JmsListener(destination = nameStreetMq)
    private void processingStreet(HashMap<String, Object> streetIn) {
        Street street = Optional.of(streetRepository.findById((String) streetIn.get(STREET_GUID))).get().orElse(new Street());
        street.setStreetGuid((String) streetIn.get(STREET_GUID));
        ofNullable((String) streetIn.get(STREET_CITY_CODE)).ifPresent(street::setCityGuid);
        ofNullable((String) streetIn.get(STREET_STREET_CODE)).ifPresent(street::setStreetCode);
        ofNullable((String) streetIn.get(STREET_FORMAL_NAME)).ifPresent(street::setStreetName);
        street.setStreetResponse((String) streetIn.get(STREET_ALL_INFO));
        streetRepository.save(street);

        String streetCode = (String) streetIn.get(STREET_CODE);
        houseService.getHousesOfStreetJkh(street.getStreetGuid(), streetCode);
    }

    private ResponseEntity<StreetJkh[]> getStreetsPackJkh(int itemPerPage, int page) {
        String url = UriComponentsBuilder.newInstance().uri(URI.create(jkhGosuslugiConfig.getUrl())).path("/streets")
                .queryParam("actual", true).queryParam("cityCode", jkhLocation.getCityCode())
                .queryParam("itemPerPage", itemPerPage).queryParam("page", page)
                .queryParam("regionCode", jkhLocation.getRegionCode()).toUriString();

        return jkhResttemplate.getForEntity(url, StreetJkh[].class);
    }
}
