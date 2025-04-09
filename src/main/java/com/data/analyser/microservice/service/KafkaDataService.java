package com.data.analyser.microservice.service;

import com.data.analyser.microservice.model.Data;

public interface KafkaDataService {

    void handle(Data data);

}
