package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.models.User;
import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManager.dtos.requests.LoginRequest;
import africa.semicolon.passwordManager.dtos.requests.RegisterUserRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManager.dtos.responses.*;
import africa.semicolon.passwordManager.exception.UserAccountException;
import africa.semicolon.passwordManager.utils.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository theUserRepository;

    @Override
    public RegisterResponse registerUser(RegisterUserRequest request) {
        User theUser = ModelMapper.map_U(request);
//        log.info(String.valueOf(theUser));

        if (theUserRepository.existsByEmail(theUser.getEmail())) {
            throw new UserAccountException("An Account exist with this email");
        }
        if (theUserRepository.existsByAccountName(theUser.getAccountName())) {
            throw new UserAccountException("Account name Unavailable");
        }
        User newUser = theUserRepository.save(theUser);
//        log.info(String.valueOf(newUser));
        return ModelMapper.map_R(newUser);

    }

    @Override
    public UserRepository getRepository() {
        return theUserRepository;
    }


    @Override
    public LoginUserResponse userLogin(LoginRequest login) {
        User theUser = theUserRepository.findUsersByAccountName(login.getAccountName());
        if (theUser == null) throw new UserAccountException("Account not found");
        boolean credentialsMatch = theUser.getAccountPassword().equals(login.getAccountPassword());
        if (credentialsMatch)log.info ("Login Successful");
        else throw new UserAccountException("Invalid details");

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage("Welcome "+theUser.getAccountName());
        response.setPasswordAccounts(theUser.getPasswordAccounts());

        return response;
    }

    @Override
    public FindUserResponse findByAccountName(String accountName) {
        User theUser = theUserRepository.findUsersByAccountName(accountName);
        return ModelMapper.map_f(theUser);

    }

    @Override
    public FindUserResponse findByEmail(String email) {
        User theUser = theUserRepository.findUsersByEmail(email);
        return ModelMapper.map_f(theUser);

    }


    @Override
    public UpdateUserResponse updateUserProfile(UpdateUserRequest updateRequest) {
        User updatedUser = theUserRepository.findUsersByAccountName(updateRequest.getAccountName());
        if (updatedUser == null) throw new UserAccountException("Account not found");
                         boolean isUpdate = false;
        if (updatedUser.getAccountPassword().equals(updateRequest.getAccountPassword())) {

            if (!(updateRequest.getNewAccountPassword()== null || updateRequest.getNewAccountPassword().trim().equals(""))){
                updatedUser.setAccountPassword(updateRequest.getNewAccountPassword());
                isUpdate =true;
            }
            if (!(updateRequest.getNewAccountName()== null || updateRequest.getNewAccountName().trim().equals(""))){
            updatedUser.setAccountName(updateRequest.getNewAccountName());
            isUpdate =true;
            }
            if (!(updateRequest.getEmail()== null || updateRequest.getEmail().trim().equals(""))) {
                updatedUser.setEmail(updateRequest.getEmail());
                isUpdate = true;
            }
            if (!(updateRequest.getLastName()== null || updateRequest.getLastName().trim().equals(""))) {
                updatedUser.setLastName(updateRequest.getLastName());
                isUpdate = true;
            }
            if (!(updateRequest.getFirstName()== null || updateRequest.getFirstName().trim().equals(""))) {
                updatedUser.setFirstName(updateRequest.getFirstName());
                isUpdate = true;
            }
        }

        if (isUpdate == true){
        theUserRepository.save(updatedUser);}

        UpdateUserResponse response = new UpdateUserResponse();
        response.setAccountName(updatedUser.getAccountName());
        response.setFullName(updatedUser.getLastName()+" "+ updatedUser.getFirstName());
        response.setEmail(updateRequest.getEmail());
        response.setMessage("Your Profile has been updated successfully");

        return response;
    }

    @Override
    public DeleteUserResponses deleteUser(DeleteUserRequest request) {
        User theUser = theUserRepository.findUsersByAccountName(request.getAccountName());
        if (theUser == null) throw new UserAccountException("Account not found");
        boolean credentialsMatch = theUser.getAccountPassword().equals(request.getAccountPassword());
        if (credentialsMatch) theUserRepository.delete(theUser);
        else throw new UserAccountException("Invalid details");
        DeleteUserResponses responses = new DeleteUserResponses();
        responses.setMessage("Account Deleted Successfully");
        return responses;

    }




    @Override
    public long count() {
        return theUserRepository.count();
    }
}

;
