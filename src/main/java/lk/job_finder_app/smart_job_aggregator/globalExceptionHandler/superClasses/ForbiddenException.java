package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
