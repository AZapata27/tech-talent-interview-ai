package co.com.flypass.exception;

public class AlreadyExistException extends BaseException{

    public AlreadyExistException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}
