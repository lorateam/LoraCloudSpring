package com.eternal.lora.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // 注解生成 DAO
public interface AddressRepository extends JpaRepository<Address, Long> { // 泛型，第一个为数据表对应的实体类，第二个为 Id
}
