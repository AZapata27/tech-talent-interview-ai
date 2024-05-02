package co.com.flypass.exception;


public class ValidationException extends BaseException{

    public ValidationException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}
