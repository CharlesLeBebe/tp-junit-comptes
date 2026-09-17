package charles.tp_junit_comptes.exceptions;

public class CompteInconnuException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CompteInconnuException(String message) {
        super(message);
    }
}
