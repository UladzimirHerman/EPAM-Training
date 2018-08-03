package com.gmail.herman.uladzimir.validator;

import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String REGEX_PHONE_NUMBER = "375[0-9]{9}";
    private static final int MAX_LENGTH_LOGIN = 255;
    private static final int MIN_LENGTH_PASSWORD = 6;
    private static final int MAX_LENGTH_NAME = 100;
    private static final int MAX_LENGTH_SURNAME = 100;
    private static final int LENGTH_PHONE_NUMBER = 12;
    private static final int MAX_LENGTH_CITY = 25;
    private static final int MAX_LENGTH_STREET = 25;
    private static final int MAX_LENGTH_BUILDING = 5;
    private static final int MAX_LENGTH_HOUSING = 5;
    private static final int MAX_LENGTH_APARTMENT = 5;
    private static final int MAX_LENGTH_NOTE = 255;

    public boolean isUserCorrect(User user) {
        return isLoginCorrect(user.getLogin()) &&
                isPasswordCorrect(user.getPassword());
    }

    public boolean isUserInfoCorrect(UserInfo userInfo) {
        return isNameCorrect(userInfo.getName()) &&
                isSurnameCorrect(userInfo.getSurname()) &&
                isPhoneCorrect(userInfo.getPhone()) &&
                isCityCorrect(userInfo.getCity()) &&
                isStreetCorrect(userInfo.getStreet()) &&
                isBuildingCorrect(userInfo.getBuilding()) &&
                isHousingCorrect(userInfo.getHousing()) &&
                isApartmentCorrect(userInfo.getApartment()) &&
                isNoteCorrect(userInfo.getNote());
    }

    public boolean isPasswordChangeCorrect
            (String currentPassword, String realPassword, String newPassword, String newPasswordRepeat) {
        return isCurrentPasswordCorrect(currentPassword, realPassword) &&
                isPasswordCorrect(newPassword) &&
                isEquallyPasswords(newPassword, newPasswordRepeat);
    }

    private boolean isLoginCorrect(String login) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(login);
        return login.length() <= MAX_LENGTH_LOGIN && matcher.matches();
    }

    private boolean isPasswordCorrect(String password) {
        return password.length() >= MIN_LENGTH_PASSWORD;
    }

    private boolean isCurrentPasswordCorrect(String password, String realPassword) {
        return BCrypt.checkpw(password, realPassword);
    }

    private boolean isEquallyPasswords(String passwordOne, String passwordTwo) {
        return passwordOne.equals(passwordTwo);
    }

    private boolean isNameCorrect(String name) {
        return !name.isEmpty() &&
                name.length() <= MAX_LENGTH_NAME;
    }

    private boolean isSurnameCorrect(String surname) {
        return !surname.isEmpty() &&
                surname.length() <= MAX_LENGTH_SURNAME;
    }

    private boolean isPhoneCorrect(String phone) {
        Pattern pattern = Pattern.compile(REGEX_PHONE_NUMBER);
        Matcher matcher = pattern.matcher(phone);
        return phone.length() == LENGTH_PHONE_NUMBER && matcher.matches();
    }

    private boolean isCityCorrect(String city) {
        return !city.isEmpty() &&
                city.length() <= MAX_LENGTH_CITY;
    }

    private boolean isStreetCorrect(String street) {
        return !street.isEmpty() &&
                street.length() <= MAX_LENGTH_STREET;
    }

    private boolean isBuildingCorrect(String building) {
        return !building.isEmpty() &&
                building.length() <= MAX_LENGTH_BUILDING;
    }

    private boolean isHousingCorrect(String housing) {
        return housing.length() <= MAX_LENGTH_HOUSING;
    }

    private boolean isApartmentCorrect(String apartment) {
        return apartment.length() <= MAX_LENGTH_APARTMENT;
    }

    private boolean isNoteCorrect(String note) {
        return note.length() <= MAX_LENGTH_NOTE;
    }

}