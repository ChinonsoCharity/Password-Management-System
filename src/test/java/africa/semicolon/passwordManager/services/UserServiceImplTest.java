package africa.semicolon.passwordManager.services;

import africa.semicolon.passwordManager.data.repository.UserRepository;
import africa.semicolon.passwordManager.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManager.dtos.requests.RegisterUserRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManager.dtos.responses.DeleteUserResponses;
import africa.semicolon.passwordManager.dtos.responses.FindUserResponse;
import africa.semicolon.passwordManager.dtos.responses.RegisterResponse;
import africa.semicolon.passwordManager.dtos.responses.UpdateUserResponse;
import africa.semicolon.passwordManager.exception.UserAccountException;
import africa.semicolon.passwordManager.utils.UserAccountValidations;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService service;
    @Autowired
    UserRepository userRepository;

    RegisterUserRequest request;
    RegisterUserRequest request2;
    RegisterUserRequest request3;

    @BeforeEach
    void setUp() {
        request = new RegisterUserRequest();
        request2 = new RegisterUserRequest();
        request3 = new RegisterUserRequest();
    }

    @AfterEach()
    void tearDown() {
        userRepository.deleteAll();
    }

//todo refactor your test

    @Test
    void registerUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setLastName("ete");
        request.setFirstName("vin");
        request.setEmail("ete@gmail.com");
        request.setAccountName("Vin King");
        request.setAccountPassword("12345");
        RegisterResponse response = service.registerUser(request);

        assertEquals(1L, service.count());
        var result = service.findByAccountName("Vin King");
        assertThat(result.getEmail(), is("ete@gmail.com"));
        assertThat(result.getAccountName(), is("Vin King"));
        assertThat(result.getFullName(), is("ete vin"));
        assertEquals("Registration Successful", response.getMessage());

    }

    @Test
    public void testEmailValidation() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("ete@gmail.com");
        assertTrue(EmailValidator.getInstance()
                .isValid(request.getEmail()));
    }

    @Test
    public void testUsingPasswordValidator() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setAccountPassword("microsoft@123");
        assertTrue(UserAccountValidations.validatePassword(request.getAccountPassword()));
    }

    @Test
    void findUserByUsername() {
        request.setLastName("ete");
        request.setFirstName("vin");
        request.setEmail("ete@gmail.com");
        request.setAccountName("Vin King");
        request.setAccountPassword("12345");

        request2.setLastName("john");
        request2.setFirstName("ven");
        request2.setEmail("john@gmail.com");
        request2.setAccountName("Ven King");
        request2.setAccountPassword("72345");

        service.registerUser(request);
        service.registerUser(request2);

        FindUserResponse response = service.findByAccountName("Ven King");
        //@todo complete assertions - done
        assertEquals("john@gmail.com", response.getEmail());
        var result = service.findByAccountName("Ven King");
        assertThat(result.getEmail(), is("john@gmail.com"));
        assertThat(result.getAccountName(), is("Ven King"));
        assertThat(result.getFullName(), is("john ven"));

    }

    @Test
    void findUserByEmail() {

        request.setLastName("ete");
        request.setFirstName("vin");
        request.setEmail("ete@gmail.com");
        request.setAccountName("Vin King");
        request.setAccountPassword("12345");

        RegisterUserRequest request1 = new RegisterUserRequest();
        request2.setLastName("john");
        request2.setFirstName("ven");
        request2.setEmail("john@gmail.com");
        request2.setAccountName("Ven King");
        request2.setAccountPassword("72345");
        service.registerUser(request);
        service.registerUser(request2);
        FindUserResponse response = service.findByEmail("john@gmail.com");
        //todo complete assertions-done
        assertEquals("Ven King", response.getAccountName());
        var result = service.findByEmail("john@gmail.com");
        assertThat(result.getEmail(), is("john@gmail.com"));
        assertThat(result.getAccountName(), is("Ven King"));
        assertThat(result.getFullName(), is("john ven"));
    }

    @Test
    void deleteUser() {

        request.setLastName("petre");
        request.setFirstName("van");
        request.setEmail("petre@gmail.com");
        request.setAccountName("pet King");
        request.setAccountPassword("Peter12&");

        request2.setLastName("mary");
        request2.setFirstName("ven");
        request2.setEmail("mary@gmail.com");
        request2.setAccountName("Mary King");
        request2.setAccountPassword("mary745$");

        ;
        request3.setLastName("mark");
        request3.setFirstName("paul");
        request3.setEmail("mark@gmail.com");
        request3.setAccountName("Paul King");
        request3.setAccountPassword("paulo56#");

        service.registerUser(request);
        service.registerUser(request2);
        service.registerUser(request3);

        assertEquals(3L, service.count());

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        //todo account name has to be deleted rom request
        deleteUserRequest.setAccountName("Mary King");
        deleteUserRequest.setAccountPassword("mary745$");

        DeleteUserResponses responseB = service.deleteUser(deleteUserRequest);
        assertEquals(2L, service.count());
        assertEquals("Account Deleted Successfully", responseB.getMessage());
        assertThrows(UserAccountException.class,()->service.findByAccountName("Mary King") );


    }

    @Test
    void updateUser() {
        request.setLastName("petre");
        request.setFirstName("van");
        request.setEmail("petre@gmail.com");
        request.setAccountName("pet King");
        request.setAccountPassword("Peter12&");

        request2.setLastName("mary");
        request2.setFirstName("ven");
        request2.setEmail("mary@gmail.com");
        request2.setAccountName("Mary King");
        request2.setAccountPassword("mary745$");

        service.registerUser(request);
        service.registerUser(request2);
        assertEquals(2L, service.count());

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setLastName("mary");
        updateRequest.setFirstName("steve");
        updateRequest.setEmail("mary@gmail.com");
        updateRequest.setAccountName("Mary King");
        updateRequest.setAccountPassword("mary745$");
        updateRequest.setNewAccountName("Mary Queen");
        updateRequest.setNewAccountPassword("mary865$");

        UpdateUserResponse response = service.updateUserProfile(updateRequest);
        assertEquals(2L, service.count());
        assertEquals("Your Profile has been updated successfully", response.getMessage());
        var result = service.findByAccountName("Mary Queen");
        assertThat(result.getEmail(), is("mary@gmail.com"));
        assertThat(result.getAccountName(), is("Mary Queen"));
        assertThat(result.getFullName(), is("mary steve"));


    }

}

