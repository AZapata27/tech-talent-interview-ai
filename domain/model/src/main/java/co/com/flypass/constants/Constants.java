package co.com.flypass.constants;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String CLIENT_DELETED_MESSAGE = "User deleted successfully";
    public static final String DEFAULT_MESSAGE_ERROR = "Ocurrió un error procesando la solicitud, por favor contacta al administrador del sistema";

}
