package africa.semicolon.passwordManager.controller;

import africa.semicolon.passwordManager.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManager.dtos.requests.LoginRequest;
import africa.semicolon.passwordManager.dtos.requests.RegisterUserRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManager.dtos.responses.*;
import africa.semicolon.passwordManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watPasswordManager/user")
@CrossOrigin(origins="http://localhost:3000/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request){
        try{
            return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest login){
        try{
            return new ResponseEntity<>(userService.userLogin(login), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accountName/{keyword}")
    public ResponseEntity<?> findByUserAccountName(@PathVariable("keyword") String accountName){
        try {
            return new ResponseEntity<>(userService.findByAccountName(accountName),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{keyword}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("keyword") String email){
        try {
            return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateProfile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserRequest newRequest){

        try {
            return new ResponseEntity<>(userService.updateUserProfile(newRequest),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest deleteRequest){
        try {
            return new ResponseEntity<>(userService.deleteUser(deleteRequest),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false,ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }


}
