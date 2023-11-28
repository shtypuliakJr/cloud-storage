package edu.nau.cs.meta.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9019852775876183752L;

    private String userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<DeviceDTO> devices;

}
