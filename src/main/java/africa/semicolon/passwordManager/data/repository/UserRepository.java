package africa.semicolon.passwordManager.data.repository;

import africa.semicolon.passwordManager.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findUsersByEmail(String email);
    User findUsersByAccountName(String accountName);
    boolean existsByEmail(String email);
    boolean existsByAccountName(String accountName);

}
