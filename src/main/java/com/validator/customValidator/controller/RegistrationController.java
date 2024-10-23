package com.validator.customValidator.controller;

import com.validator.customValidator.model.RegistrationForm;
import com.validator.customValidator.validator.RegistrationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/register")
public class RegistrationController {

  @Autowired
  private RegistrationFormValidator registrationFormValidator;

  @PostMapping("/user")
  public ResponseEntity<String> registerUser(@RequestBody final RegistrationForm registrationForm) {
    Errors errors = new BeanPropertyBindingResult(registrationForm, "registrationForm");
    registrationFormValidator.validate(registrationForm, errors);
    if (errors.hasErrors()) {
      String errorMessage = errors.getAllErrors().stream()
                  .map(ObjectError::getDefaultMessage)
                  .collect(Collectors.joining(" & "));
      return ResponseEntity.badRequest().body("Illegal arguments, " + errorMessage);
    }
    return ResponseEntity.ok("Registered successfully");
  }
}
