package finalcasetask.mapper;

import finalcasetask.payload.dto.UserReviewDTO;
import finalcasetask.payload.entity.UserReview;
import finalcasetask.payload.request.UserReviewRequest;
import finalcasetask.payload.request.UserReviewUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {

    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    UserReview converToUserReview (UserReviewRequest userReviewRequest);

    UserReviewDTO converToUserReviewDTO(UserReview userReview);

    @Mapping(target = "id" , ignore = true)
    void updateUserReviewFields(@MappingTarget UserReview userReview , UserReviewUpdateRequest request);


}
