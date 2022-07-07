package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.*;
import africa.semicolon.passwordManager.dtos.responses.*;

public interface UserService {
    RegisterResponse registerUser(RegisterUserRequest request);

    UserRepository getRepository();

    FindUserResponse findByAccountName(String accountName);

    FindUserResponse findByEmail(String email);

    UpdateUserResponse updateUserProfile(UpdateUserRequest userRequest);

    DeleteUserResponses deleteUser(DeleteUserRequest request);

    LoginUserResponse userLogin(LoginRequest login);

    long count();

}