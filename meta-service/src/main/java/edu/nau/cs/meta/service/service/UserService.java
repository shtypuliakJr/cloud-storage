package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUser(String userId);

}
