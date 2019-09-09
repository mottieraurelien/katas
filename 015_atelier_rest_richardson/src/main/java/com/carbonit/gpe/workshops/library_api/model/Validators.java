package com.carbonit.gpe.workshops.library_api.model;

import com.carbonit.gpe.workshops.library_api.FeaturesConfig;
import com.carbonit.gpe.workshops.library_api.service.exceptions.DataValidationException;
import io.swagger.model.UserPartial;
import org.apache.commons.validator.routines.EmailValidator;

public class Validators {

    /**
     * Author name and title must be non null and valid
     */
    public static void validateReference(IReference reference) {
        if(FeaturesConfig.isValidationDisabled()){
            return;
        }

        if (reference.getAuthorName() == null
                || reference.getTitle() == null
                || invalidInputLength(reference.getAuthorName(), 5, 50)
                || invalidInputLength(reference.getTitle(), 5, 200))
            throw new DataValidationException("must provide valid author and title");
    }


    /**
     * validate every non null attributes
     */
    public static void validateUser(IUser user) {
        if(FeaturesConfig.isValidationDisabled()){
            return;
        }

        if (user.getEmail() != null) {
            validateEmail(user.getEmail());
        }
        if (user.getSurname() != null) {
            validateLastName(user.getSurname());
        }
        if (user.getName() != null) {
            validateFirstName(user.getName());
        }
    }

    /**
     * email and password must be non null and valid
     */
    public static void validateCredentials(UserPartial userPartial) {
        if(FeaturesConfig.isValidationDisabled()){
            return;
        }

        validateEmail(userPartial.getEmail());
        validatePassword(userPartial.getPassword());
    }

    public static void validateEmail(String email) {
        if(FeaturesConfig.isValidationDisabled()){
            return;
        }

        if (invalidInputLength(email, 4, 150) ||
                !EmailValidator.getInstance().isValid(email))
            throw new DataValidationException("provided email must be valid");
    }

    private static void validateFirstName(String firstName) {
        if (invalidInputLength(firstName, 3, 25) ||
                !firstName.matches("[A-Z][a-zA-Z]*")) {
            throw new DataValidationException("provided firstname must be valid");
        }
    }

    private static void validateLastName(String lastName) {
        if (invalidInputLength(lastName, 3, 64) ||
                !lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))
            throw new DataValidationException("provided surname must be valid");
    }

    private static void validatePassword(String password) {
        if (invalidInputLength(password, 3, 64) ||
                !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
            throw new DataValidationException("provided password must be valid");
    }

    private static boolean invalidInputLength(String input, int min, int max) {
        return input == null || input.length() < min || input.length() > max;
    }

}
