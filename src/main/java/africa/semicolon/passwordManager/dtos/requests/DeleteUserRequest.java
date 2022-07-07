package africa.semicolon.passwordManager.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DeleteUserRequest {
    private String accountName;
    private String accountPassword;
}
