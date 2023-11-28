package edu.nau.cs.meta.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
public class DeviceDTO {

    private String id;

    private String deviceName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String userId;

}
