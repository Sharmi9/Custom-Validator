package com.validator.customValidator.validator;

import com.validator.customValidator.model.RegistrationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationFormValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return RegistrationForm.class.equals(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {
    RegistrationForm registrationForm = (RegistrationForm) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Invalid", "First name is required");
    if (registrationForm.getEmail().isEmpty()) {
      errors.reject("Invalid", "Email is required");
    }
    if (registrationForm.getAge() < 10) {
      errors.reject("Invalid","Age must be greater than 10");
    }
    if (!registrationForm.getEmail().contains("@")) {
      errors.reject("Invalid","Please provide a valid email");
    }
    if (!(registrationForm.getMobileNumber().length() == 10)) {
      errors.reject("Invalid", "Please enter a valid mobile number");
    }
  }
}
