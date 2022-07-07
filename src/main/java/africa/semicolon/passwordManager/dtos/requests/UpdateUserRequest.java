package africa.semicolon.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String accountName;
    private String accountPassword;
    private String lastName;
    private String firstName;
    private String email;
    private String newAccountName;
    private String newAccountPassword;
}
