package com.eternal.lora.service.serviceImpl;

import com.eternal.lora.domain.*;
import com.eternal.lora.dto.DataDto;
import com.eternal.lora.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private BoxesRepository boxesRepository;
    private AddressRepository addressRepository;
    private DataRepository dataRepository;
    private SensorsRepository sensorsRepository;

    @Autowired // 自动在容器中寻找对应接口的实现并装载，不需要显式调用该方法
    public void setRepository(BoxesRepository boxesRepository,
                              AddressRepository addressRepository,
                              DataRepository dataRepository,
                              SensorsRepository sensorsRepository) {
        this.boxesRepository = boxesRepository;
        this.addressRepository = addressRepository;
        this.dataRepository = dataRepository;
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public String postData(String uuid, String sensorInfo) {
        Boxes boxes = boxesRepository.findByUuid(uuid);
        if (boxes == null) {
            return "error: uuid didn't register";
        }
//        Data data = new Data();
        return " ";
    }

    @Override
    public List<Address> getAllAddressInfo() {
        return addressRepository.findAll();
    }

    @Override
    public List<Boxes> getOneAddressBoxInfo(int addressId) {
        return boxesRepository.findByAddressId(addressId);
    }

    @Override
    public List<DataDto> getOneAddressAllCurrentSensorInfo(int addressId) {
        List<Data> dataList = dataRepository.selectAllSensorCurrentData(addressId);
        List<DataDto> dataDtoList = new ArrayList<>();
        for (Data data: dataList) {
            String sensorName = data.getSensorName();
            Sensors sensors = sensorsRepository.findByName(sensorName);

            DataDto dataDto = DataDto.createFrom(data, sensors);

            dataDtoList.add(dataDto);
        }
        return dataDtoList;
    }

    @Override
    public DataDto getOneAddressOneCurrentSensorInfo(int addressId, String sensorName) {
        Sensors sensors = sensorsRepository.findByName(sensorName);
        Data data = dataRepository.selectOneSensorCurrentData(addressId, sensorName);
        return DataDto.createFrom(data, sensors);
    }

    @Override
    public List<DataDto> getOneAddressOneHistorySensorInfo(int addressId, String sensorName, int amount) {
        Sensors sensors = sensorsRepository.findByName(sensorName);
        List<Data> dataList = dataRepository.getOneAddressOneHistoryHighSensorInfo(addressId, sensorName, amount);
        List<DataDto> dataDtoList = new ArrayList<>();
        for (Data data: dataList) {
            DataDto dataDto = DataDto.createFrom(data, sensors);
            dataDtoList.add(dataDto);
        }
        return dataDtoList;
    }
}
