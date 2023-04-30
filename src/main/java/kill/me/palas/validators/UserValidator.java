package kill.me.palas.validators;

import kill.me.palas.models.User;
import kill.me.palas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (user.getStatus() != null){
            if (user.getStatus().getId() == 2){
                errors.rejectValue("password","Block.user");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }

    public void up_validate(Object o, Errors errors, Object o2) {
        User user = (User) o;
        User user2 = (User) o2;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (user.getUsername().equals(user2.getUsername())){
                errors.rejectValue("username", "Equals.userForm.username");
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), user2.getPassword())) {
            errors.rejectValue("password", "Different.userForm.password");
        }
    }

    public void pass_validate(Object o, Errors errors, Object o2) {
        User user = (User) o;
        User user2 = (User) o2;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (!bCryptPasswordEncoder.matches(user.getOldPassword(), user2.getPassword())){
            errors.rejectValue("oldPassword", "Different.userForm.password");
        }

        if (bCryptPasswordEncoder.matches(user.getPassword(), user2.getPassword())) {
            errors.rejectValue("password", "Equals.userForm.password");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }

}
