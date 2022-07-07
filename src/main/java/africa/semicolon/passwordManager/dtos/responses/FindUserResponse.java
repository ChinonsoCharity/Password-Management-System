package africa.semicolon.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class FindUserResponse {
    private String email;
    private String accountName;
    private String fullName;
}
