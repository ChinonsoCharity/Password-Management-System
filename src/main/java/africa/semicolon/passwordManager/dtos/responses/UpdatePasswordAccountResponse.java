package africa.semicolon.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class UpdatePasswordAccountResponse {
    private String webSite;
    private String newUsername;
    private String newEmail;
    private String newPassword;
    private String message;
}
