package cnab.normalization.bycoders.domain.exception;

public class SaveTransactionException extends RuntimeException {
    public SaveTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
