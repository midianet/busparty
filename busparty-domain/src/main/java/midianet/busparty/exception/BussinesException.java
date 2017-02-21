package midianet.busparty.exception;

public abstract class BussinesException extends RuntimeException {

    public BussinesException(final String message) {
        super(message);
    }

    public BussinesException(String message, final Throwable cause) {
        super(message, cause);
    }

    public BussinesException(final Throwable cause) {
        super(cause);
    }

    public BussinesException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
