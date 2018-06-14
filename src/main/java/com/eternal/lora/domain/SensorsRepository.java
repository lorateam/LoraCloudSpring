package com.eternal.lora.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SensorsRepository extends JpaRepository<Sensors, Long> {
    Sensors findByName(String name);
}
