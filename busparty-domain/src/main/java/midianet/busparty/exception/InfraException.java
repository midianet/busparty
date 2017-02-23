package midianet.busparty.exception;

public class InfraException  extends RuntimeException{

    public InfraException(final String message) {
        super(message);
    }

    public InfraException(String message, final Throwable cause) {
        super(message, cause);
    }

    public InfraException(final Throwable cause) {
        super(cause);
    }

    public InfraException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}