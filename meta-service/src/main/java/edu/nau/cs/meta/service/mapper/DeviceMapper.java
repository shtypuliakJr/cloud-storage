package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.DeviceDTO;
import edu.nau.cs.meta.service.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

    public DeviceDTO mapEntityToDTO(Device device) {
        return DeviceDTO.builder()
                .withId(device.getId())
                .withDeviceName(device.getDeviceName())
                .withCreatedAt(device.getCreatedAt())
                .withUpdatedAt(device.getUpdatedAt())
                .withUserId(device.getUser().getId())
                .build();
    }

    public Device mapDTOToEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .withId(deviceDTO.getId())
                .withDeviceName(deviceDTO.getDeviceName())
                .withCreatedAt(deviceDTO.getCreatedAt())
                .withUpdatedAt(deviceDTO.getUpdatedAt())
                .build();
    }

}
