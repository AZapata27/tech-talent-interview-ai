package co.com.flypass.exception;

public class NotFoundException extends BaseException{

    public NotFoundException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}
