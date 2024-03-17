package finalcasetask.controller;

import finalcasetask.general.RestResponse;
import finalcasetask.payload.dto.UserReviewDTO;
import finalcasetask.payload.request.UserReviewRequest;
import finalcasetask.payload.request.UserReviewUpdateRequest;
import finalcasetask.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/userReviews")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewService userReviewService;



    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUserReview(@RequestBody UserReviewRequest request){

        UserReviewDTO userReviewDTO = userReviewService.save(request);

        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserReview (@PathVariable Long id){

        userReviewService.deleteUserReview(id);
    }

    @PutMapping("/{userReviewId}")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateUserReview (@PathVariable Long userReviewId,
                                                             @RequestBody UserReviewUpdateRequest request){

        UserReviewDTO userReviewDTO = userReviewService.updateUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }
}
