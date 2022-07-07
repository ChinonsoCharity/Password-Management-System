package africa.semicolon.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class PasswordAccountRequest {
    private String accountName;
    private String accountPassword;
    private String website;
    private String username;
    private String email;
    private String websitePassword;
}
