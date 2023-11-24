package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.UserDTO;
import edu.nau.cs.meta.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getUserId())
                .userName(userDTO.getUserName())
                .userEmail(userDTO.getUserEmail())
                .userPassword(userDTO.getUserPassword())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .lastLoginAt(userDTO.getLastLoginAt())
                .build();
    }

    public UserDTO mapUserToDTO(User user) {
        return UserDTO.builder()
                .withUserId(user.getId())
                .withUserName(user.getUserName())
                .withUserEmail(user.getUserEmail())
                .withUserPassword(user.getUserPassword())
                .withCreatedAt(user.getCreatedAt())
                .withUpdatedAt(user.getUpdatedAt())
                .withLastLoginAt(user.getLastLoginAt())
                .build();
    }

}
