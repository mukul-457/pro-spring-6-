package spring.examples.five.aspectjannotation;

public class RejectedInstrumentException extends RuntimeException{
    private static final long serialVersionUID = 3L;

    public RejectedInstrumentException(String message){
        super(message);
    }

    public RejectedInstrumentException(String message, Throwable cause){
        super(message, cause);
    }
}
