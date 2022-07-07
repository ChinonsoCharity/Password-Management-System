package africa.semicolon.passwordManager.dtos.requests;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
@Data
public class RegisterUserRequest {
    private String lastName;
    private String firstName;
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message="Invalid Email")
    @Email
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "username must be of 6 to 12 length with no special characters")
    private String accountName;
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String accountPassword;

}
