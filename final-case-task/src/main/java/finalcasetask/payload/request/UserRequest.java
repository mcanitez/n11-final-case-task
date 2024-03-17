package finalcasetask.payload.request;

import finalcasetask.payload.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "Please enter your name")
    @Size(min = 2 , max = 20, message = "Your name should be at least 2 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull(message = "Please enter your last name")
    @Size(min = 2 , max = 30, message = "Your last name should be at least 2 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your last name must consist of the characters .")
    private String lastName;

    @NotNull(message = "Please enter your email")
    @Email(message = "Please enter valid email")
    @Size(min = 5, max = 50, message = "Your email should be at least 5 chars")
    private String email;

    @NotNull(message = "Please enter your phone number")
    @Size(min=12, max=12, message = "Phone number should be exact 12 chars")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    private String phoneNumber;

    private double latitude;

    private double longitude;

    @NotNull(message = "Please enter your gender")
    private Gender gender;
}
