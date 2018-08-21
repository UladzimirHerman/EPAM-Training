package com.epam.herman.uladzimir.validator;

import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.entity.UserInfo;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@link UserValidator} is used to check the data members of {@link User}
 * and {@link UserInfo}. Also this class contains some other validation methods.
 *
 * @author Uladzimir Herman
 */
public class UserValidator {

    private static final String REGEX_EMAIL =
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
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

    /**
     * Check the user
     *
     * @param user user for checking
     * @return true, if correct or false, if incorrect
     */
    public boolean isUserCorrect(User user) {
        return isLoginCorrect(user.getLogin()) &&
                isPasswordCorrect(user.getPassword());
    }

    /**
     * Check the user information
     *
     * @param userInfo user information for checking
     * @return true, if correct or false, if incorrect
     */
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

    /**
     * Check the process of changing the password
     *
     * @param currentPassword   current password
     * @param realPassword      entered current password for confirmation of change
     * @param newPassword       new password
     * @param newPasswordRepeat repeat a new password
     * @return true, if correct or false, if incorrect
     */
    public boolean isPasswordChangeCorrect
    (String currentPassword, String realPassword, String newPassword, String newPasswordRepeat) {
        return isCurrentPasswordCorrect(currentPassword, realPassword) &&
                isPasswordCorrect(newPassword) &&
                isEquallyPasswords(newPassword, newPasswordRepeat);
    }

    /**
     * Check the login
     *
     * @param login login for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isLoginCorrect(String login) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(login);
        return login.length() <= MAX_LENGTH_LOGIN && matcher.matches();
    }

    /**
     * Check the length of the user password
     *
     * @param password user password
     * @return true, if correct or false, if incorrect
     */
    private boolean isPasswordCorrect(String password) {
        return password.length() >= MIN_LENGTH_PASSWORD;
    }

    /**
     * Check the confirmation of the password
     *
     * @param password     current user password
     * @param realPassword entered current password for confirmation
     * @return true, if correct or false, if incorrect
     */
    private boolean isCurrentPasswordCorrect(String password, String realPassword) {
        return BCrypt.checkpw(password, realPassword);
    }

    /**
     * Check the equality new passwords
     *
     * @param passwordOne new password
     * @param passwordTwo repeat a new password
     * @return true, if correct or false, if incorrect
     */
    private boolean isEquallyPasswords(String passwordOne, String passwordTwo) {
        return passwordOne.equals(passwordTwo);
    }

    /**
     * Check the name
     *
     * @param name name for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isNameCorrect(String name) {
        return !name.isEmpty() &&
                name.length() <= MAX_LENGTH_NAME;
    }

    /**
     * Check the surname
     *
     * @param surname surname for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isSurnameCorrect(String surname) {
        return !surname.isEmpty() &&
                surname.length() <= MAX_LENGTH_SURNAME;
    }

    /**
     * Check the phone number
     *
     * @param phone phone number for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isPhoneCorrect(String phone) {
        Pattern pattern = Pattern.compile(REGEX_PHONE_NUMBER);
        Matcher matcher = pattern.matcher(phone);
        return phone.length() == LENGTH_PHONE_NUMBER && matcher.matches();
    }

    /**
     * Check the city name
     *
     * @param city city name for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isCityCorrect(String city) {
        return !city.isEmpty() &&
                city.length() <= MAX_LENGTH_CITY;
    }

    /**
     * Check the street name
     *
     * @param street street name for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isStreetCorrect(String street) {
        return !street.isEmpty() &&
                street.length() <= MAX_LENGTH_STREET;
    }

    /**
     * Check the building number
     *
     * @param building building number for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isBuildingCorrect(String building) {
        return !building.isEmpty() &&
                building.length() <= MAX_LENGTH_BUILDING;
    }

    /**
     * Check the housing number
     *
     * @param housing housing number for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isHousingCorrect(String housing) {
        return housing.length() <= MAX_LENGTH_HOUSING;
    }

    /**
     * Check the apartment number
     *
     * @param apartment apartment number for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isApartmentCorrect(String apartment) {
        return apartment.length() <= MAX_LENGTH_APARTMENT;
    }

    /**
     * Check the length of the note
     *
     * @param note note for checking
     * @return true, if correct or false, if incorrect
     */
    private boolean isNoteCorrect(String note) {
        return note.length() <= MAX_LENGTH_NOTE;
    }

}