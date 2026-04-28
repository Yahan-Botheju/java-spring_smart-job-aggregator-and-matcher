package lk.job_finder_app.smart_job_aggregator.globalResponseHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponse<T> {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private T data;
}
