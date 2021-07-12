package cl.utem.project.cpyd.exception;

public class Exceptions extends RuntimeException{
    private final Integer httpCode;
    //private static final Logger LOG = Logger.getLogger(Exceptions.class.getName());

    public Exceptions() {
        super("Precondicion Fallida");
        this.httpCode = 412;
    }

    public Exceptions(String message) {
        super(message);
        this.httpCode = 412;
    }
    
    public Exceptions(Integer httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
        this.httpCode = 412;
    }

    public Exceptions(Throwable cause) {
        super(cause);
        this.httpCode = 500;
    }   

    public Integer getHttpCode() {
        return httpCode;
    }

    
    
    
    
}
