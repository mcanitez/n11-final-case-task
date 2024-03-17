package finalcasetask.payload.dto;

import finalcasetask.payload.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private Gender gender;
}
