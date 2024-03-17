package finalcasetask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import finalcasetask.payload.dto.UserReviewDTO;
import finalcasetask.payload.entity.UserReview;
import finalcasetask.payload.entity.enums.Score;
import finalcasetask.payload.request.UserReviewRequest;
import finalcasetask.repository.UserReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserReviewServiceTest {

    @Mock
    private UserReviewRepository userReviewRepository;

    @InjectMocks
    private UserReviewService userReviewService;

    @Test
    void saveUserReviewTest() {
        // Arrange
        UserReviewRequest request = new UserReviewRequest();
        request.setRestaurantId(2L);
        request.setComment("Thank you");
        request.setScore(Score.FOUR);

        UserReview userReview = new UserReview();
        userReview.setId(1L);
        userReview.setRestaurantId(1L);
        userReview.setComment("Thank you");
        userReview.setScore(Score.FOUR);

        when(userReviewRepository.save(any(UserReview.class))).thenReturn(userReview);

        // Act
        UserReviewDTO result = userReviewService.save(request);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getRestaurantId());
        assertEquals("Thank you", result.getComment());
        assertEquals(Score.FOUR, result.getScore());
    }
}
