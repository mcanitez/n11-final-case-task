package finalcasetask.payload.request;

import finalcasetask.payload.entity.enums.Score;

public record UserReviewUpdateRequest (Long id,
                                       String comment,
                                       Score score) {
}
