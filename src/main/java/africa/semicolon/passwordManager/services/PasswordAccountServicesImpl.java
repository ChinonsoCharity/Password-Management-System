package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import africa.semicolon.passwordManager.data.models.User;
import africa.semicolon.passwordManager.data.repository.PasswordAccountRepository;
import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.PasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdatePasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.responses.DeletePasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.FindPasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.SavedPasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.UpdatePasswordAccountResponse;
import africa.semicolon.passwordManager.exception.PasswordAccountException;
import africa.semicolon.passwordManager.exception.PasswordManagerNotFoundException;
import africa.semicolon.passwordManager.exception.UserAccountException;
import africa.semicolon.passwordManager.utils.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordAccountServicesImpl implements PasswordAccountService{
    @Autowired
    private PasswordAccountRepository thePasswordAccountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public SavedPasswordAccountResponse addPasswordAccount(PasswordAccountRequest request) {
        User theUser = userRepository.findUsersByAccountName(request.getAccountName());
        if (theUser == null) throw new UserAccountException("Account not found");
       if (theUser.getAccountPassword().equals(request.getAccountPassword())){
            PasswordAccount thePasswordAccount = ModelMapper.map_P(request);
            if (thePasswordAccountRepository.existsByWebsite(request.getWebsite()))
                throw  new PasswordAccountException("Account with this website already exist");
            PasswordAccount saved = thePasswordAccountRepository.save(thePasswordAccount);
            theUser.getPasswordAccounts().add(saved);
            userRepository.save(theUser);
       }
       else throw new UserAccountException("Invalid login details");

       return ModelMapper.map_saveP(theUser);
    }

    @Override
    public PasswordAccountRepository getRepository() {
        return thePasswordAccountRepository;
    }

    @Override
    public FindPasswordAccountResponse findAccountWebsite(String theWebsite) {
        PasswordAccount thePasswordAccount = thePasswordAccountRepository.findByWebsite(theWebsite);
        return ModelMapper.map_findP(thePasswordAccount);

    }

    @Override
    public List<UpdatePasswordAccountResponse> updatePasswordAccount(UpdatePasswordAccountRequest request) {
        User theUser = userRepository.findUsersByAccountName(request.getAccountName());
        if (theUser == null) throw new UserAccountException("Account not found");
        List<PasswordAccount> someList = theUser.getPasswordAccounts();
        List<UpdatePasswordAccountResponse> responses = new ArrayList<>();

        someList.forEach(thePasswordAccount -> {
            boolean isUpdated = false;
            if (thePasswordAccount.getWebsite().equals(request.getWebsite())) {
                if (!(request.getNewWebsitePassword() == null || request.getNewWebsitePassword().trim().equals(""))) {
                    thePasswordAccount.setWebsitePassword(request.getNewWebsitePassword());
                    isUpdated = true;
                }
                if (!(request.getNewUsername() == null || request.getNewUsername().trim().equals(""))) {
                    thePasswordAccount.setUsername(request.getNewUsername());
                    isUpdated = true;
                }
                if (isUpdated == true) {
                    thePasswordAccountRepository.save(thePasswordAccount);
                }
            }
            responses.add(ModelMapper.map_updateP(thePasswordAccount));
        });

        return responses;


    }

    @Override
    public DeletePasswordAccountResponse deleteAccount(String website) {
        PasswordAccount thePasswordAccount = thePasswordAccountRepository.findByWebsite(website);
        if (thePasswordAccount == null)throw new PasswordManagerNotFoundException("Account does not Exist");
        thePasswordAccountRepository.delete(thePasswordAccount);
        DeletePasswordAccountResponse response = new DeletePasswordAccountResponse();
        response.setMessage("Account Deleted Successfully");
        return response;
    }

    @Override
    public long count() {
        return thePasswordAccountRepository.count();
    }
}
