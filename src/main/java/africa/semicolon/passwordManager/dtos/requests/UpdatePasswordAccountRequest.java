package africa.semicolon.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class UpdatePasswordAccountRequest {
    private String accountName;
    private String accountPassword;
    private String website;
    private String newUsername;
    private String newEmail;
    private String newWebsitePassword;
}
