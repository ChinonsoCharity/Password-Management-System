package africa.semicolon.passwordManager.dtos.requests;

import jakarta.validation.constraints.Email;

public class AddUserRequest {
    private String lastName;
    private String firstName;
    @Email
    private String email;
    private String accountName;
    private String accountPassword;
}
