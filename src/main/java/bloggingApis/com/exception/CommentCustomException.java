package bloggingApis.com.exception;

public class CommentCustomException extends RuntimeException {
    public CommentCustomException(String message) {
        super(message);
    }
}