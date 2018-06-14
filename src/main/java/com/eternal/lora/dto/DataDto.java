package com.eternal.lora.dto;

import com.eternal.lora.domain.Data;
import com.eternal.lora.domain.Sensors;

import java.sql.Timestamp;

public class DataDto {

    private String name;
    private String unit;
    private double value;
    private Timestamp time;
    private String sensorName;
    private int addressId;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    private void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    private void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTime() {
        return time;
    }

    private void setTime(Timestamp time) {
        this.time = time;
    }

    public String getSensorName() {
        return sensorName;
    }

    private void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getAddressId() {
        return addressId;
    }

    private void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public static DataDto createFrom(Data data, Sensors sensors) {
        DataDto dataDto = new DataDto();
        dataDto.setName(sensors.getNote());
        dataDto.setUnit(sensors.getUnit());
        dataDto.setValue(data.getValue());
        dataDto.setTime(data.getTime());
        dataDto.setSensorName(data.getSensorName());
        dataDto.setAddressId(data.getAddressId());

        return dataDto;
    }
}
