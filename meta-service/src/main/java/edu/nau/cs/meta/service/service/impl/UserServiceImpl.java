package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.UserDTO;
import edu.nau.cs.meta.service.entity.FolderObject;
import edu.nau.cs.meta.service.entity.User;
import edu.nau.cs.meta.service.exception.CsUserAlreadyExistsException;
import edu.nau.cs.meta.service.exception.CsUserDoesNotExistsByIdException;
import edu.nau.cs.meta.service.mapper.UserMapper;
import edu.nau.cs.meta.service.repository.FolderObjectRepository;
import edu.nau.cs.meta.service.repository.UserRepository;
import edu.nau.cs.meta.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final FolderObjectRepository folderObjectRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) throws CsUserAlreadyExistsException {

        if (!userRepository.existsByUserName(userDTO.getUserName())) {
            User user = userMapper.mapUserToEntity(userDTO);
            user.setLastLoginAt(LocalDateTime.now());
            user = userRepository.save(user);
            folderObjectRepository.save(FolderObject.builder()
                    .withFolderName(user.getUserName())
                    .withFolderPath(user.getUserName())
                    .withParentFolder(null)
                    .withUser(user)
                    .build());
            return userMapper.mapUserToDTO(user);
        }
        throw new CsUserAlreadyExistsException(userDTO.getUserName());
    }

    @Override
    public UserDTO getUser(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapUserToDTO)
                .orElseThrow(() -> new CsUserDoesNotExistsByIdException(userId));
    }

}
