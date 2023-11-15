package com.yutsuki.telegram.service;

import com.yutsuki.telegram.model.request.NotificationsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class NotificationService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public void sendNotification(NotificationsRequest message){

    emitters.forEach(emitter ->{
        try {
            emitter.send(SseEmitter.event().data(message, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            log.error("Error sending notification {}", e.getMessage());
            emitter.complete();
            emitters.remove(emitter);
        }
    });
    }

    public void addEmitter(SseEmitter emitter){
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
    }

}
