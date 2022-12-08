package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.NoticeInformation;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

@Component
public class NoticeInformationValidator implements Validator {
    private final NoticeService noticeService;

    public NoticeInformationValidator(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public boolean supports(Class<?> clazz) {
        return NoticeInformation.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            // do something
            /*UserCredentials enterForm = (UserCredentials) target;
            if (userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.rejectValue(
                        "password", "password.invalid-login-or-password", "Invalid login or password");
            }*/
        }
    }
}
