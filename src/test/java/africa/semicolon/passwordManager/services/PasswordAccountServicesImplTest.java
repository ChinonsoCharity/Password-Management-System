package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.models.PasswordAccount;
import africa.semicolon.passwordManager.data.repository.PasswordAccountRepository;
import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.PasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.requests.RegisterUserRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdatePasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.responses.DeletePasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.FindPasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.SavedPasswordAccountResponse;
import africa.semicolon.passwordManager.dtos.responses.UpdatePasswordAccountResponse;
import africa.semicolon.passwordManager.exception.PasswordAccountException;
import africa.semicolon.passwordManager.exception.PasswordManagerNotFoundException;
import africa.semicolon.passwordManager.exception.UserAccountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class PasswordAccountServicesImplTest {
    @Autowired
    private UserService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private  PasswordAccountService passwordAccountService;
    @Autowired
    PasswordAccountRepository passwordAccountRepository;

    PasswordAccountRequest request1;
    PasswordAccountRequest request2;
    RegisterUserRequest userRequest1;
    RegisterUserRequest userRequest2;

    @BeforeEach
    void setUp(){
        request1 = new PasswordAccountRequest();
        request2 = new PasswordAccountRequest();
        userRequest1 = new RegisterUserRequest();
        userRequest2 = new RegisterUserRequest();
    }

    @AfterEach
    void tearDown (){
        passwordAccountRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void addPasswordAccount() {
        userRequest1.setLastName("petre");
        userRequest1.setFirstName("van");
        userRequest1.setEmail("petre@gmail.com");
        userRequest1.setAccountName("petKing");
        userRequest1.setAccountPassword("petty345&");

        userRequest2.setLastName("jack");
        userRequest2.setFirstName("ven");
        userRequest2.setEmail("jack@gmail.com");
        userRequest2.setAccountName("jackKing");
        userRequest2.setAccountPassword("jacky723#");
        service.registerUser(userRequest1);
        service.registerUser(userRequest2);

        assertEquals(2L, service.count());
        request1.setAccountName("jackKing");
        request1.setAccountPassword("jacky723#");
        request1.setWebsite("whatApp");
        request1.setUsername("whitney");
        request1.setEmail("jack@gmail.com");
        request1.setWebsitePassword("Whitney34#");

        SavedPasswordAccountResponse response = passwordAccountService.addPasswordAccount(request1);
        assertEquals("Your Password details has saved Successfully",response.getMessage());
        assertEquals("whatApp",response.getPasswordAccounts().get(0).getWebsite());

        var result = passwordAccountService.findAccountWebsite("whatApp");
        assertThat(result.getEmail(), is("jack@gmail.com"));
        assertThat(result.getUsername(), is("whitney"));
        assertThat(result.getPassword(), is("Whitney34#"));
    }

    @Test
    void findAccountWebsite() {
        userRequest2.setLastName("jack");
        userRequest2.setFirstName("ven");
        userRequest2.setEmail("jack@gmail.com");
        userRequest2.setAccountName("jackKing");
        userRequest2.setAccountPassword("jacky723#");

        service.registerUser(userRequest2);

        assertEquals(1L, service.count());
        request1.setAccountName("jackKing");
        request1.setAccountPassword("jacky723#");
        request1.setWebsite("whatApp");
        request1.setUsername("whitney");
        request1.setEmail("jack@gmail.com");
        request1.setWebsitePassword("Whitney34#");

        request2.setAccountName("jackKing");
        request2.setAccountPassword("jacky723#");
        request2.setWebsite("gitHub");
        request2.setUsername("Willow");
        request2.setEmail("jack@gmail.com");
        request2.setWebsitePassword("Willow154#");

        passwordAccountService.addPasswordAccount(request1);
        passwordAccountService.addPasswordAccount(request2);

        assertEquals(2L,passwordAccountService.count());

        var result = passwordAccountService.findAccountWebsite("whatApp");
        assertThat(result.getPassword(),is("Whitney34#"));
        assertThat(result.getEmail(),is("jack@gmail.com"));
        assertThat(result.getUsername(),is("whitney"));


    }

    @Test
    void updatePasswordAccount() {
        userRequest2.setLastName("jack");
        userRequest2.setFirstName("ven");
        userRequest2.setEmail("jack@gmail.com");
        userRequest2.setAccountName("jackKing");
        userRequest2.setAccountPassword("jacky723#");

        service.registerUser(userRequest2);

        assertEquals(1L, service.count());
        request1.setAccountName("jackKing");
        request1.setAccountPassword("jacky723#");
        request1.setWebsite("whatApp");
        request1.setUsername("whitney");
        request1.setEmail("jack@gmail.com");
        request1.setWebsitePassword("Whitney34#");

        request2.setAccountName("jackKing");
        request2.setAccountPassword("jacky723#");
        request2.setWebsite("gitHub");
        request2.setUsername("Willow");
        request2.setEmail("jack@gmail.com");
        request2.setWebsitePassword("Willow154#");

        passwordAccountService.addPasswordAccount(request1);
        passwordAccountService.addPasswordAccount(request2);

        assertEquals(2L, passwordAccountService.count());

        UpdatePasswordAccountRequest updateRequest = new UpdatePasswordAccountRequest();
        updateRequest.setAccountName("jackKing");
        updateRequest.setAccountPassword("jacky723#");
        updateRequest.setWebsite("gitHub");
        updateRequest.setNewEmail("willow@gmail.com");
        updateRequest.setNewUsername("WillowQueen");
        updateRequest.setNewWebsitePassword("Willow1504#");
        passwordAccountService.updatePasswordAccount(updateRequest);
//        PasswordAccount myPasswordAccount = new PasswordAccount();
        var user = userRepository.findUsersByEmail("jack@gmail.com");
        var passwords = user.getPasswordAccounts();
        PasswordAccount ourPassword = null;
       for (PasswordAccount password: passwords){
           if (password.getWebsite().equals("gitHub"))ourPassword =password;
       }
       assertEquals("WillowQueen", ourPassword.getUsername());

    }


    @Test
    void deleteAccount() {
        userRequest2.setLastName("jack");
        userRequest2.setFirstName("ven");
        userRequest2.setEmail("jack@gmail.com");
        userRequest2.setAccountName("jackKing");
        userRequest2.setAccountPassword("jacky723#");

        service.registerUser(userRequest2);

        assertEquals(1L, service.count());
        request1.setAccountName("jackKing");
        request1.setAccountPassword("jacky723#");
        request1.setWebsite("whatApp");
        request1.setUsername("whitney");
        request1.setEmail("jack@gmail.com");
        request1.setWebsitePassword("Whitney34#");

        request2.setAccountName("jackKing");
        request2.setAccountPassword("jacky723#");
        request2.setWebsite("gitHub");
        request2.setUsername("Willow");
        request2.setEmail("jack@gmail.com");
        request2.setWebsitePassword("Willow154#");

        passwordAccountService.addPasswordAccount(request1);
        passwordAccountService.addPasswordAccount(request2);

        assertEquals(2L,passwordAccountService.count());
        DeletePasswordAccountResponse response = passwordAccountService.deleteAccount("gitHub");
        assertEquals(1L, service.count());
        assertEquals("Account Deleted Successfully", response.getMessage());

    }

}