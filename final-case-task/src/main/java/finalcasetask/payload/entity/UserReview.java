package finalcasetask.payload.entity;

import finalcasetask.payload.entity.enums.Score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_review")
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long restaurantId;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;

    private Score score;

}
