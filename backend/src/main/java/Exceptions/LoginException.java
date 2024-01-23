package Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginException extends Exception {
    public enum Code {
        USER_NOT_FOUND,
        WRONG_PASSWORD,
    }

    private final Code code;

    private final String message;

    public static LoginException userNotFound(String message) {
        return new LoginException(Code.USER_NOT_FOUND, message);
    }

    public static LoginException wrongPassword(String message) {
        return new LoginException(Code.WRONG_PASSWORD, message);
    }
}