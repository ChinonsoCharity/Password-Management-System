package africa.semicolon.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class UpdateUserResponse {
    private String email;
    private String accountName;
    private String fullName;
    private String message;
}
