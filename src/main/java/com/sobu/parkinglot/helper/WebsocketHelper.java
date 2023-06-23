package com.sobu.parkinglot.helper;

import com.sobu.parkinglot.api.bus.PositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebsocketHelper {

    private final SimpMessagingTemplate messagingTemplate;

    public void pushBusEvent(PositionDto positionDto) {
        messagingTemplate.convertAndSend("/ws-bus/" + positionDto.getBusId(),  positionDto);
    }
}
