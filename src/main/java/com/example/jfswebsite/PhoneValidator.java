package com.example.jfswebsite;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String string = (String) o;

        Pattern pattern = Pattern.compile("^\\d{9}$");
        Matcher matcher = pattern.matcher(string);
        boolean valueIsInvalid = matcher.matches();

        if (!valueIsInvalid) {
            throw new ValidatorException(new FacesMessage("Invalid phone number"));
        }
    }
}
