package co.com.flypass.exception;

public class BaseException extends RuntimeException {
    private final String technicalMessage;
    private final String userMessage;

    public BaseException(String technicalMessage, String userMessage) {
        super(technicalMessage);
        this.technicalMessage = technicalMessage;
        this.userMessage = userMessage;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
