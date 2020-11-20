package ru.kulikovskiy.jkh.jkhdataloader.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.kulikovskiy.jkh.jkhdataloader.service.StreetService;

@RequiredArgsConstructor
@Component
@Scope("prototype")
public class JkhLoaderThread extends Thread{
    private final StreetService streetService;


    @Override
    public void run() {
        streetService.getAllStreetsJkh();
    }
}
