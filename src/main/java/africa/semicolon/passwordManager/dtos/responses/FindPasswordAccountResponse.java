package africa.semicolon.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class FindPasswordAccountResponse {
    private String webSite;
    private String username;
    private String email;
    private String password;
}
