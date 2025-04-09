package com.data.analyser.microservice.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class BeanConfig {

    @Bean
    public XML consumerXML() throws IOException {
        return new XMLDocument(
                getClass().getResourceAsStream("/kafka/consumer.xml").readAllBytes()
                //new File("src/main/resources/kafka/consumer.xml")
        );
    }

}
