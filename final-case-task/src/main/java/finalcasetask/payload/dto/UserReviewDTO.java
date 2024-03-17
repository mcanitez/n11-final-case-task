package finalcasetask.payload.dto;

import finalcasetask.payload.entity.enums.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDTO {

    private Long id;

    private Long restaurantId;

    private String comment;

    private Score score;
}
