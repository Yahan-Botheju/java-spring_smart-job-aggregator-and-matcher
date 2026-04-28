package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler;

public class FrobiddenException extends RuntimeException {
  public FrobiddenException(String message) {
    super(message);
  }
}
