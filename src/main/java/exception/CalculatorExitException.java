package exception;

public class CalculatorExitException extends RuntimeException {
    public CalculatorExitException(String message) {
        super(message);
    }
}
