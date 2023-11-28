package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.DeviceDTO;
import edu.nau.cs.meta.service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.meta.service.constants.Endpoint.DEVICES;
import static edu.nau.cs.meta.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + DEVICES)
public class DeviceController {

    private final DeviceService deviceService;
    private final String userId = USER_ID;

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> getDeviceData(@PathVariable String deviceId) {
        return ResponseEntity.ok(deviceService.getDeviceData(deviceId, userId));
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getDevicesData() {
        return ResponseEntity.ok(deviceService.getDevicesData(userId));
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> saveDevice(@RequestBody DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.addDevice(deviceDTO, userId));
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> editDeviceData(@PathVariable String deviceId, @RequestBody DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.editDeviceData(deviceId, deviceDTO, userId));
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> deleteDevice(@PathVariable String deviceId) {
        deviceService.deleteDevice(deviceId, userId);
        return ResponseEntity.ok().build();
    }

}
