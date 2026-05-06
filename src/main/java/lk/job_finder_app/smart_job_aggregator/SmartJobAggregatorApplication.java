package lk.job_finder_app.smart_job_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartJobAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartJobAggregatorApplication.class, args);
    }

}
