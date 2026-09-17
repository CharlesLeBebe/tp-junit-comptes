package charles.tp_junit_comptes.exceptions;

public class CompteDejaExistantException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CompteDejaExistantException(String message) {
        super(message);
    }
}
