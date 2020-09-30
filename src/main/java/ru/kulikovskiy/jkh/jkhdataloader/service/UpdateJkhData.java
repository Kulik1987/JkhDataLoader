package ru.kulikovskiy.jkh.jkhdataloader.service;

import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateJkhData {

    private final HazelcastInstance hazelcastInstance;
    private final StreetService streetService;

    public UpdateJkhData(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance, StreetService streetService) {
        this.hazelcastInstance = hazelcastInstance;
        this.streetService = streetService;
    }

    @Scheduled(cron = "0 0 1 1 * *")
    public void running() {
        if (hazelcastInstance.getList("checkRunning").size() == 0) {
            checkRunningProcess();
            streetService.getAllStreetsJkh();
        }
    }

    public void checkRunningProcess() {
        hazelcastInstance.getList("checkRunning").add(0, Boolean.TRUE);
    }
}
