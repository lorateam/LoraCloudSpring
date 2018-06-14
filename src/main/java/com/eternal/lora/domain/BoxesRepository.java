package com.eternal.lora.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BoxesRepository extends JpaRepository<Boxes, Long> {
    Boxes findByUuid(String uuid); // 通过方法名解析生成 SQL 语句
    List<Boxes> findByAddressId(Integer addressId);
}
