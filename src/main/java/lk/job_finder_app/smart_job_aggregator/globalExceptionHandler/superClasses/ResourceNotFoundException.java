package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
