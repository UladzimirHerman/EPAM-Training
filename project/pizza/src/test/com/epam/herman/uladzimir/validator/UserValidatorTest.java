package com.epam.herman.uladzimir.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UserValidatorTest {

    private UserValidator userValidator;
    private String currentPassword;
    private String realPassword;
    private String newPassword;
    private String newPasswordRepeat;
    private boolean expected;

    public UserValidatorTest(String currentPassword, String realPassword,
                             String newPassword, String newPasswordRepeat, boolean expected) {
        userValidator = new UserValidator();
        this.currentPassword = currentPassword;
        this.realPassword = realPassword;
        this.newPassword = newPassword;
        this.newPasswordRepeat = newPasswordRepeat;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = {
                {"password", "$2a$10$2WebPKKIpkJZf4rvizvicevG.a6AcqbJ/VMfyAyu.UjDGYbh4A7ga", "newPassword", "newPassword", true},
                {"qwerty123@", "$2a$10$BJPhB8qZ.CS1X/qA0ckkm.vD5e8OsBQB5r2gsr6Dmv0swi4MbPtEq", "pswd23gh", "pswd23gh", true},
                {"password1", "$2a$10$2WebPKKIpkJZf4rvizvicevG.a6AcqbJ/VMfyAyu.UjDGYbh4A7ga", "newPassword", "newPassword", false},
                {"password", "$2a$10$.QZ9FpF..sWzbteRrdlmbOZcYU.OmYFNtoDN949mRAc3bxRcrOrea", "newPassword", "newPassword", false},
                {"password", "$2a$10$2WebPKKIpkJZf4rvizvicevG.a6AcqbJ/VMfyAyu.UjDGYbh4A7ga", "newPassword", "newPassword1", false},
                {"password", "$2a$10$2WebPKKIpkJZf4rvizvicevG.a6AcqbJ/VMfyAyu.UjDGYbh4A7ga", "newPassword1", "newPassword", false},
                {"password23", "$2a$10$2WebPKKIpkJZf4rvizvicevG.a6AcqbJ/VMfyAyu.UjDGYbh4A7ga", "newPassword1", "newPassword", false}};
        return Arrays.asList(data);
    }

    @Test
    public void isPasswordChangeCorrectTest() {
        assertEquals(expected, userValidator.isPasswordChangeCorrect
                (currentPassword, realPassword, newPassword, newPasswordRepeat));
    }

}