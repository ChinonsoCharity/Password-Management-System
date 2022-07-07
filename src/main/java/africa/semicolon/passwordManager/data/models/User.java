package africa.semicolon.passwordManager.data.models;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Data
@Document("Users")
public class User {

    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String accountName;
    private String accountPassword;
    @DBRef
    private List<PasswordAccount> passwordAccounts = new ArrayList<>();
}
