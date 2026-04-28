package lk.job_finder_app.smart_job_aggregator.globalExceptionHandler;

import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.BadRequestException;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.ForbiddenException;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.ResourceNotFoundException;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> InternalServerError(
            Exception ex,
            WebRequest webRequest
    ) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(500);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> BadRequestException(
            BadRequestException ex,
            WebRequest webRequest
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(400);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    //403
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> ForbiddenException(
            ForbiddenException ex,
            WebRequest webRequest
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(403);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    //401
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> UnauthorizedException(
            UnauthorizedException ex,
            WebRequest webRequest
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(401);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    //404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> ResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(404);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
