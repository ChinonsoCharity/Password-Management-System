package africa.semicolon.passwordManager.utils;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import africa.semicolon.passwordManager.data.models.User;
import africa.semicolon.passwordManager.dtos.requests.PasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.requests.RegisterUserRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdatePasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.responses.*;
import africa.semicolon.passwordManager.exception.UserAccountException;

public class ModelMapper {
    public static User map_U(RegisterUserRequest request){
        User theUser =new User();
        theUser.setLastName(request.getLastName());
        theUser.setFirstName(request.getFirstName());
        theUser.setEmail(request.getEmail());
        theUser.setAccountName(request.getAccountName());
        theUser.setAccountPassword(request.getAccountPassword());
        return theUser;
    }
    public static RegisterResponse map_R(User theUser){
        RegisterResponse response = new RegisterResponse();
        response.setFullName(theUser.getLastName()+" "+theUser.getFirstName());
        response.setEmail(theUser.getEmail());
        response.setAccountName(theUser.getAccountName());
        response.setMessage("Registration Successful");
        return response;
    }

    public static FindUserResponse map_f(User theUser){
        if (theUser== null)throw new UserAccountException("Account not found");
        FindUserResponse response = new FindUserResponse();
        response.setFullName(theUser.getLastName()+" "+theUser.getFirstName());
        response.setEmail(theUser.getEmail());
        response.setAccountName(theUser.getAccountName());
        return response;
    }
    public static PasswordAccount map_P (PasswordAccountRequest request){
        PasswordAccount newPasswordAccount = new PasswordAccount();
        newPasswordAccount.setWebsite(request.getWebsite());
        newPasswordAccount.setEmail(request.getEmail());
        newPasswordAccount.setWebsitePassword(request.getWebsitePassword());
        newPasswordAccount.setUsername(request.getUsername());
        return newPasswordAccount;
    }
    public static SavedPasswordAccountResponse map_saveP(User theUser){
        SavedPasswordAccountResponse response = new SavedPasswordAccountResponse();
        response.setAccountName(theUser.getAccountName());
        response.setPasswordAccounts(theUser.getPasswordAccounts());
        response.setMessage("Your Password details has saved Successfully");
        return response;
    }

    public static FindPasswordAccountResponse map_findP(PasswordAccount thePasswordAccount){
        FindPasswordAccountResponse response = new FindPasswordAccountResponse();
        response.setWebSite(thePasswordAccount.getWebsite());
        response.setEmail(thePasswordAccount.getEmail());
        response.setUsername(thePasswordAccount.getUsername());
        response.setPassword(thePasswordAccount.getWebsitePassword());
        return response;
    }

    public static UpdatePasswordAccountResponse map_updateP(PasswordAccount thePasswordAccount){
        UpdatePasswordAccountResponse response = new UpdatePasswordAccountResponse();
        response.setWebSite(thePasswordAccount.getWebsite());
        response.setNewEmail(thePasswordAccount.getEmail());
        response.setNewUsername(thePasswordAccount.getUsername());
        response.setNewPassword(thePasswordAccount.getWebsitePassword());
        response.setMessage("Your Password details has been update Successfully");
        return response;
    }
}
