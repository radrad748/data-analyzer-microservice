package com.data.analyser.microservice.service;

import com.data.analyser.microservice.model.Data;
import com.data.analyser.microservice.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final DataRepository dataRepository;

    @Override
    public void handle(Data data) {
        log.info("Data object {} was saved: ",  data);
        dataRepository.save(data);
    }
}
