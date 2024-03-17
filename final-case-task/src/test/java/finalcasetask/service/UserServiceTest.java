package finalcasetask.service;

import finalcasetask.payload.dto.UserDTO;
import finalcasetask.payload.entity.Users;
import finalcasetask.payload.entity.enums.Gender;
import finalcasetask.payload.request.UserRequest;
import finalcasetask.payload.request.UserUpdateRequest;
import finalcasetask.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void save_UserRequest_ReturnsUserDTO() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setGender(Gender.MALE);
        request.setPhoneNumber("123-456-7890");
        request.setLatitude(45.87);
        request.setLongitude(03.54);

        Users user = new Users();
        user.setId(1L);
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setGender(Gender.MALE);
        user.setLatitude(45.87);
        user.setLongitude(03.54);
        user.setPhoneNumber("123-456-7890");

        when(userRepository.save(any(Users.class))).thenReturn(user);

        // Act
        UserDTO result = userService.save(request);

        // Assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());

        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    public void deleteUser_ValidUserId_UserDeletedSuccessfully() {
        // Arrange
        Long userId = 1L;

        Users user = new Users();
        user.setId(userId);
        user.setName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setGender(Gender.MALE);
        user.setLatitude(45.87);
        user.setLongitude(03.54);
        user.setPhoneNumber("123-456-7890");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
    @Test
    public void updateUser_ValidUserId_UserUpdatedSuccessfully() {
        // Arrange
        Long userId = 1L;

        UserUpdateRequest updateRequest = new UserUpdateRequest(userId,"John","Doe","john.doe@example.com","123-456-7890",45.87,03.54);

        Users existingUser = new Users();
        existingUser.setId(userId);
        existingUser.setName("Jane");
        existingUser.setLastName("Smith");
        existingUser.setEmail("jane.smith@example.com");
        existingUser.setGender(Gender.FEMALE);
        existingUser.setLatitude(99.87);
        existingUser.setLongitude(36.54);
        existingUser.setPhoneNumber("123-456-8888");


        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserDTO updatedUserDTO = userService.updateUser(updateRequest);

        // Assert
        assertNotNull(updatedUserDTO);
        assertEquals(userId, updatedUserDTO.getId());
        assertEquals(updateRequest.name(), updatedUserDTO.getName());
        assertEquals(updateRequest.lastName(), updatedUserDTO.getLastName());
        assertEquals(updateRequest.email(), updatedUserDTO.getEmail());
        assertEquals(updateRequest.phoneNumber(), updatedUserDTO.getPhoneNumber());
        assertEquals(updateRequest.latitude(), updatedUserDTO.getLatitude());
        assertEquals(updateRequest.longitude(), updatedUserDTO.getLongitude());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(Users.class));
    }
}