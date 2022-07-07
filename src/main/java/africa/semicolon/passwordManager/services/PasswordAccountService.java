package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.repository.PasswordAccountRepository;
import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManager.dtos.requests.PasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdatePasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.responses.*;

import java.util.List;

public interface PasswordAccountService {
    SavedPasswordAccountResponse addPasswordAccount(PasswordAccountRequest request);

    PasswordAccountRepository getRepository();

    FindPasswordAccountResponse findAccountWebsite(String website);

    List<UpdatePasswordAccountResponse> updatePasswordAccount(UpdatePasswordAccountRequest request);

    DeletePasswordAccountResponse deleteAccount(String website);

    long count();




}
