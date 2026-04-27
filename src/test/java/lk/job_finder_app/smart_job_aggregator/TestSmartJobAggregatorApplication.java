package lk.job_finder_app.smart_job_aggregator;

import org.springframework.boot.SpringApplication;

public class TestSmartJobAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.from(SmartJobAggregatorApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
