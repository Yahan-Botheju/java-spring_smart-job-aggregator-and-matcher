package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
