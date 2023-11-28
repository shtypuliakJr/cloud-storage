package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.DeviceDTO;

import java.util.List;

public interface DeviceService {

    DeviceDTO getDeviceData(String deviceId, String userId);

    List<DeviceDTO> getDevicesData(String userId);

    DeviceDTO addDevice(DeviceDTO deviceDTO, String userId);

    DeviceDTO editDeviceData(String deviceId, DeviceDTO deviceDTO, String userId);

    void deleteDevice(String deviceId, String userId);

}
