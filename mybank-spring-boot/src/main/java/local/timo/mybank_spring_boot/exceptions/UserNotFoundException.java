package local.timo.mybank_spring_boot.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId) {
        super("Couldn't find user with id: " + userId);
    }
}
