package pt.nunoaleixogoncalves.example.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by nunogoncalves
 * on mar, 2022
 */
public class ExampleException extends RuntimeException {

    private static final String UNKNOWN_ERROR_MSG = "error with no message";

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ExampleException() {
        super(UNKNOWN_ERROR_MSG);
    }

    public ExampleException(HttpStatus httpStatus) {
        super(UNKNOWN_ERROR_MSG);
        setHttpStatus(httpStatus);
    }

    public ExampleException(String message) {
        super(message);
    }

    public ExampleException(HttpStatus httpStatus, String message) {
        super(message);
        setHttpStatus(httpStatus);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        if (!httpStatus.isError()) {
            throw new IllegalArgumentException(
                    "code " + httpStatus.value()
                            + " its not error type");
        }
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
