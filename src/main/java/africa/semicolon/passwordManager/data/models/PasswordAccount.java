package africa.semicolon.passwordManager.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Password Accounts")
public class PasswordAccount {
    @Id
    private String id;
    private String website;
    private String username;
    private String email;
    private String websitePassword;
}
