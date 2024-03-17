package finalcasetask.service;

import finalcasetask.exception.ResourceNotFoundException;
import finalcasetask.general.Messages;
import finalcasetask.mapper.UserMapper;
import finalcasetask.payload.dto.UserDTO;
import finalcasetask.payload.entity.Users;
import finalcasetask.payload.request.UserRequest;
import finalcasetask.payload.request.UserUpdateRequest;
import finalcasetask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO save(UserRequest request) {

        Users user = UserMapper.INSTANCE.convertToUser(request);

        user = userRepository.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    public void deleteUser(Long id) {

        Users user = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE,id)));

        userRepository.deleteById(id);
    }

    public UserDTO updateUser(UserUpdateRequest request) {

        Users user = userRepository.findById(request.id()).orElseThrow(()->
                new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE2)));

        UserMapper.INSTANCE.updateUserFields(user,request);

        Users updatedUser = userRepository.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(updatedUser);
    }
}
