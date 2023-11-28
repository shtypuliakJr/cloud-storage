package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.DeviceDTO;
import edu.nau.cs.meta.service.entity.Device;
import edu.nau.cs.meta.service.entity.User;
import edu.nau.cs.meta.service.exception.CsUserDoesNotExistsByIdException;
import edu.nau.cs.meta.service.exception.DeviceDoesNotExists;
import edu.nau.cs.meta.service.mapper.DeviceMapper;
import edu.nau.cs.meta.service.repository.DeviceRepository;
import edu.nau.cs.meta.service.repository.UserRepository;
import edu.nau.cs.meta.service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceDTO> getDevicesData(String userId) {
        return deviceRepository.findAllByUserId(userId).stream()
                .map(deviceMapper::mapEntityToDTO)
                .toList();
    }

    @Override
    public DeviceDTO getDeviceData(String deviceId, String userId) {
        return deviceRepository.findByIdAndUserId(deviceId, userId)
                .map(deviceMapper::mapEntityToDTO)
                .orElseThrow(() -> new DeviceDoesNotExists(deviceId, userId));
    }

    @Override
    public DeviceDTO addDevice(DeviceDTO deviceDTO, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CsUserDoesNotExistsByIdException(userId));

        Device device = Device.builder()
                .withDeviceName(deviceDTO.getDeviceName())
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withUser(user)
                .build();

        return deviceMapper.mapEntityToDTO(deviceRepository.save(device));
    }

    @Override
    public DeviceDTO editDeviceData(String deviceId, DeviceDTO deviceDTO, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CsUserDoesNotExistsByIdException(userId));

        Device device = Device.builder()
                .withId(deviceId)
                .withDeviceName(deviceDTO.getDeviceName())
                .withCreatedAt(deviceDTO.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .withUser(user)
                .build();

        return deviceMapper.mapEntityToDTO(deviceRepository.save(device));
    }

    @Override
    public void deleteDevice(String deviceId, String userId) {
        deviceRepository.deleteByIdAndUserId(deviceId, userId);
    }

}
