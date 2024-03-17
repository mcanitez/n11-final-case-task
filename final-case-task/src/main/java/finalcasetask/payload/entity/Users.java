package finalcasetask.payload.entity;

import finalcasetask.payload.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please enter your name")
    private String name;

    @NotNull(message = "Please enter your last name")
    private String lastName;

    @NotNull(message = "Please enter your email")
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private double latitude;

    private double longitude;

    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserReview> reviews;

}
