package africa.semicolon.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String accountName;
    private String accountPassword;
}
