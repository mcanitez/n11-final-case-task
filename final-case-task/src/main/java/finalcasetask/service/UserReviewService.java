package finalcasetask.service;

import finalcasetask.exception.ResourceNotFoundException;
import finalcasetask.general.Messages;
import finalcasetask.mapper.UserReviewMapper;
import finalcasetask.payload.dto.UserReviewDTO;
import finalcasetask.payload.entity.UserReview;
import finalcasetask.payload.request.UserReviewRequest;
import finalcasetask.payload.request.UserReviewUpdateRequest;
import finalcasetask.repository.UserReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;

    public UserReviewDTO save(UserReviewRequest request) {

            UserReview userReview = UserReviewMapper.INSTANCE.converToUserReview(request);

        userReview = userReviewRepository.save(userReview);

            return UserReviewMapper.INSTANCE.converToUserReviewDTO(userReview);
        }

    public void deleteUserReview(Long id) {

        UserReview userReview = userReviewRepository.findById(id).orElseThrow(()->
                new ResolutionException(String.format(Messages.NOT_FOUND_USER_REVIEW_MESSAGE,id)));

        userReviewRepository.deleteById(id);
    }


    public UserReviewDTO updateUserReview(UserReviewUpdateRequest request) {

        UserReview userReview = userReviewRepository.findById(request.id()).orElseThrow(()->
                new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_REVIEW_MESSAGE2)));

        UserReviewMapper.INSTANCE.updateUserReviewFields(userReview,request);

        userReview.setComment(request.comment());
        userReview.setScore(request.score());

        UserReview updatedUserReview = userReviewRepository.save(userReview);

        return UserReviewMapper.INSTANCE.converToUserReviewDTO(updatedUserReview);
    }
}

