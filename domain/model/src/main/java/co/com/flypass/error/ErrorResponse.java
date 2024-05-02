package co.com.flypass.error;

public record ErrorResponse(String exceptionName, String userMessage, String technicalMessage) {

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }


    public static class ErrorResponseBuilder {
        private String exceptionName;
        private String userMessage;
        private String technicalMessage;

        ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder exceptionName(String exceptionName) {
            this.exceptionName = exceptionName;
            return this;
        }

        public ErrorResponseBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ErrorResponseBuilder technicalMessage(String technicalMessage) {
            this.technicalMessage = technicalMessage;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this.exceptionName, this.userMessage, this.technicalMessage);
        }

        @Override
        public String toString() {
            return "ErrorResponseBuilder{" +
                    "exceptionName='" + exceptionName + '\'' +
                    ", userMessage='" + userMessage + '\'' +
                    ", technicalMessage='" + technicalMessage + '\'' +
                    '}';
        }
    }

}
