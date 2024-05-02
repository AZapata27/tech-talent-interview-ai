package co.com.flypass.exception;

public class BadRequestException extends BaseException{

    public BadRequestException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}

