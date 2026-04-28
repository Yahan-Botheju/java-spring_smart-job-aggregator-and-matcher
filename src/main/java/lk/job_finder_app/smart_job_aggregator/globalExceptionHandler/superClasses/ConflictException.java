package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
