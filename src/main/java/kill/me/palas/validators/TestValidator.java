package kill.me.palas.validators;

import kill.me.palas.models.Answer;
import kill.me.palas.models.Test;
import kill.me.palas.models.User;
import kill.me.palas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TestValidator implements Validator {

         @Override
        public boolean supports(Class<?> aClass) {
        return Test.class.equals(aClass);
    }
        @Override
        public void validate(Object o, Errors errors) {
            Test test = (Test) o;
        }

        public void answers_validate(Object o, Errors errors){
            Answer answer = (Answer) o;
            if(answer.getId() == 0) errors.rejectValue("answer", "Answer.Not.Null");
        }
    }

