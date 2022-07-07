package africa.semicolon.passwordManager.data.repository;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordAccountRepository extends MongoRepository<PasswordAccount,String> {
    boolean existsByWebsite(String website);
    PasswordAccount findByWebsite(String website);
}
