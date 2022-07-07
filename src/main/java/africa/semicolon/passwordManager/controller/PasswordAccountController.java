package africa.semicolon.passwordManager.controller;
import africa.semicolon.passwordManager.dtos.requests.PasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.requests.UpdatePasswordAccountRequest;
import africa.semicolon.passwordManager.dtos.responses.*;
import africa.semicolon.passwordManager.services.PasswordAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/watPasswordManager/user/passwordAccount")
public class PasswordAccountController {

    @Autowired
    private PasswordAccountService passwordAccountService;

    @PostMapping("/addPasswordAccount")
    public ResponseEntity<?> addPasswordAccount(@RequestBody PasswordAccountRequest addRequest){
        try{
            return new ResponseEntity<>(passwordAccountService.addPasswordAccount(addRequest), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{keyword}")
    public ResponseEntity<?>  findAccountWebsite(@PathVariable("keyword") String website){
        try{
            return new ResponseEntity<>(passwordAccountService.findAccountWebsite(website), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updatePasswordAccount(@RequestBody UpdatePasswordAccountRequest newRequest){
        try{
            return new ResponseEntity<>(passwordAccountService.updatePasswordAccount(newRequest), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{website}")
     public ResponseEntity<?> deletePasswordAccount(@PathVariable String website){
        try{
            return new ResponseEntity<>(passwordAccountService.deleteAccount(website), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

}
