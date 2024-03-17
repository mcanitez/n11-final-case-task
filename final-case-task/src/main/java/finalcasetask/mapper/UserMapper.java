package finalcasetask.mapper;

import finalcasetask.payload.dto.UserDTO;
import finalcasetask.payload.entity.Users;
import finalcasetask.payload.request.UserRequest;
import finalcasetask.payload.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users convertToUser (UserRequest userRequest);

    UserDTO convertToUserDTO (Users user);

    @Mapping(target = "id" , ignore = true)
    void updateUserFields (@MappingTarget Users user, UserUpdateRequest request);
}
