package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
