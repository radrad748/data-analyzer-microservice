package com.data.analyser.microservice.service;

import com.data.analyser.microservice.config.LocalDateTimeDeserializer;
import com.data.analyser.microservice.model.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    private final KafkaReceiver<String, Object> receiver;
    private final LocalDateTimeDeserializer ldtDeserializer;
    private final KafkaDataService kafkaDataService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    private void init() {

        if("prod".equals(activeProfile))
            fetch();

    }


    @Override
    public void fetch() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, ldtDeserializer).create();

        receiver.receive()
                .subscribe(r -> {
                    Data data = gson.fromJson(r.value().toString(), Data.class);
                    kafkaDataService.handle(data);
                    r.receiverOffset().acknowledge();
                });
    }
}
