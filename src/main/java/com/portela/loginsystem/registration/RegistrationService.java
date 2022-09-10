package com.portela.loginsystem.registration;

import com.portela.loginsystem.appuser.AppUser;
import com.portela.loginsystem.appuser.AppUserRole;
import com.portela.loginsystem.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }

        return appUserService.singUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
