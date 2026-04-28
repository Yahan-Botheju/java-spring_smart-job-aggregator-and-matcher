package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
