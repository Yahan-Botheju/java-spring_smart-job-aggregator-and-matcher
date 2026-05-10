package lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExternalJobResponseDTO(
        @JsonProperty("results")List<TheMuseJob> jobs
        ) {
    public record TheMuseJob(
            String name,
            String content,
            String company,
            List<Location> locations
    ) {}

    public record Company(String name){}
    public record Location(String name){}
}
