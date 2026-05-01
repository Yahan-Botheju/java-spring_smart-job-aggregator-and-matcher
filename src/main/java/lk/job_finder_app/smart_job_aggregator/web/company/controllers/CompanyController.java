package lk.job_finder_app.smart_job_aggregator.web.company.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.company.CompanyUseCase;
import lk.job_finder_app.smart_job_aggregator.web.company.DTOs.CompanyRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.company.DTOs.CompanyResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.company.webMappers.CompanyWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobsapplicator/company")
@RequiredArgsConstructor
public class CompanyController {

    //inject company use case
    private final CompanyUseCase companyUseCase;

    //inject web mapper
    private final CompanyWebMapper companyWebMapper;

    //get all companies
    @GetMapping("/")
    public ResponseEntity<StandardResponse<List<CompanyResponseDTO>>> getAllCompanies(){
        List<Company> companies = companyUseCase.getAllCompanies();

        List<CompanyResponseDTO> responseDTOS = companies.stream().map(companyWebMapper::toResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Companies details fetched successfully",
                LocalDateTime.now(),
                responseDTOS
        ));

    }

    //create company
    @PostMapping("/")
    public ResponseEntity<StandardResponse<CompanyResponseDTO>> createCompany(
                               @RequestBody CompanyRequestDTO companyRequestDTO
    ){
        //turn dot to domain model
        Company company = companyWebMapper.toDomainModel(companyRequestDTO);

        //set to usecase
        Company savedEntity = companyUseCase.createCompany(company);

        return ResponseEntity.created(URI.create("/api/v1/jobsapplicator/company/"))
                .body(new StandardResponse<>(
                        201,
                        "Company registered successfully",
                        LocalDateTime.now(),
                        companyWebMapper.toResponseDTO(savedEntity)
                ));
    }

}
