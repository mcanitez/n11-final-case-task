package finalcasetask.payload.request;

import finalcasetask.payload.entity.enums.Score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewRequest {

    private Long restaurantId;


    @NotBlank(message = "Comment is required")
    @Size(min = 2 , max = 150, message = "Comment should be between 2 and 150 characters")
    private String comment;

    @Range(min = 1, max = 5, message = "Score must be between 1 and 5")
    @NotNull(message = "Score is required")
    private Score score;
}
