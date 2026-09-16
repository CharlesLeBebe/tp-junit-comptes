package charles.tp_junit_comptes.exceptions;

public class MontantInvalideException extends RuntimeException {

    private static final long serialVersionUID = 1L; //pour enlever message d'erreur en rapport avec le runtimeexception

    public MontantInvalideException(String message) {
        super(message);
    }
}