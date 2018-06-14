package com.eternal.lora.service;

import com.eternal.lora.domain.Address;
import com.eternal.lora.domain.Boxes;
import com.eternal.lora.dto.DataDto;

import javax.transaction.Transactional;
import java.util.List;

// 定义接口，controller 中面向该接口编程，不关心具体实现
public interface MessageService {

    @Transactional // @Transactional 注解该方法为一个事务，在操作数据库失败时回滚到初始状态
    String postData(String uuid, String sensorInfo);

    List<Address> getAllAddressInfo(); // 查询操作一般是没有副作用的，不需要回滚

    List<Boxes> getOneAddressBoxInfo(int addressId);

    List<DataDto> getOneAddressAllCurrentSensorInfo(int addressId);

    DataDto getOneAddressOneCurrentSensorInfo(int addressId, String sensorName);

    List<DataDto> getOneAddressOneHistorySensorInfo(int addressId, String sensorName, int amount);
}
