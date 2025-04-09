package com.data.analyser.microservice.repository;

import com.data.analyser.microservice.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
