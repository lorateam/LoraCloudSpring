package com.eternal.lora.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DataRepository extends JpaRepository<Data, Long> {
    // @Query 注解使用自定义 SQL 语句，nativeQuery 表示使用原生 SQL 语句，冒号表示变量
    @Query(value = "select * from ( select * from data where address_id = :addressId order by time desc ) b group by sensor_name",
    nativeQuery = true)
    List<Data> selectAllSensorCurrentData(@Param("addressId") int addressId); // @Param 注解插入变量

    @Query(value = "select * from data where address_id = :addressId and  sensor_name = :sensorName order by time desc limit 1",
    nativeQuery = true)
    Data selectOneSensorCurrentData(@Param("addressId") int addressId, @Param("sensorName") String sensorName);

    @Query(nativeQuery = true,
    value = "select * from data where address_id = :addressId and sensor_name = :sensorName order by time limit :amount")
    List<Data> getOneAddressOneHistoryHighSensorInfo(@Param("addressId") int addressId,
                                                     @Param("sensorName") String sensorName,
                                                     @Param("amount") int amount);
}
