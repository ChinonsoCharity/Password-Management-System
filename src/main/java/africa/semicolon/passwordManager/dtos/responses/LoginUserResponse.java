package africa.semicolon.passwordManager.dtos.responses;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
@Data
public class LoginUserResponse {
    private String message;
    @DBRef
    private List<PasswordAccount> passwordAccounts = new ArrayList<>();
}
