package bloggingApis.com.exception;

public class UserCustomException extends RuntimeException {
    private int id;
    public UserCustomException(String message, int id) {
        super(String.format(message,id));
        this.id = id;
    }
}
