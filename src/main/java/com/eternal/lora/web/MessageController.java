package com.eternal.lora.web;

import com.eternal.lora.domain.Address;
import com.eternal.lora.domain.Boxes;
import com.eternal.lora.dto.DataDto;
import com.eternal.lora.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 自动将该类下所有方法注解为 ResponseBody，返回值将会被自动转化为 Json
@RequestMapping(value = "/action")
public class MessageController {

    private MessageService messageService; // 声明 Service 服务

    @Autowired // 自动在容器中寻找对应接口的实现并装载，不需要显式调用该方法
    public void registerService(MessageService messageService) {
        this.messageService = messageService;
    }

    // TODO POST DATA 待完成
    @RequestMapping(value = "/postdata") // 路由分发
    public void postData(@RequestHeader(value = "uuid") String uuid, // 从 Header 中提取数据
                         @RequestHeader(value = "sensorInfo") String sensorInfo) {
    }

    @GetMapping(value = "/baseInfo/address") // 限制方法为 GET
    public ResponseEntity<List<Address>> getAllAddressInfo() { // ResponseEntity 可以设置 header，status 等 http 字段
        List<Address> result = messageService.getAllAddressInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/baseInfo/address/boxes")
    public List<Boxes> getOneAddressBoxInfo(@RequestParam(value = "addressId") Integer addressId) { // @RequestParam 从查询参数中提取数据
        return messageService.getOneAddressBoxInfo(addressId); // 直接返回 Json
    }

    @GetMapping(value = "/currentInfo/address/boxes")
    public List<DataDto> getOneAddressAllCurrentSensorInfo(@RequestParam(value = "addressId") Integer addressId) {
        return messageService.getOneAddressAllCurrentSensorInfo(addressId);
    }

    @GetMapping(value = "/currentInfo/address/boxes/sensor")
    public DataDto getOneAddressOneCurrentSensorInfo(@RequestParam(value = "addressId") int addressId,
                                                     @RequestParam(value = "sensorName") String sensorName) {
        return messageService.getOneAddressOneCurrentSensorInfo(addressId, sensorName);
    }

    @GetMapping(value = "/historyData/address/boxes/sensor")
    public List<DataDto> getOneAddressOneHistorySensorInfo(@RequestParam(value = "addressId") int addressId,
                                                           @RequestParam(value = "sensorName") String sensorName,
                                                           @RequestParam(value = "amount", defaultValue = "10") int amount) { // 提供默认参数
        return messageService.getOneAddressOneHistorySensorInfo(addressId, sensorName, amount);
    }
}
