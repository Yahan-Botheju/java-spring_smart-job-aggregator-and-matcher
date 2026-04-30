package lk.job_finder_app.smart_job_aggregator.web.company.webMappers;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.web.company.DTOs.CompanyRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.company.DTOs.CompanyResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyWebMapper {

    //requestDTO to domain model
    Company toDomainModel(CompanyRequestDTO companyRequestDTO);

    //domain mode to responseDTO
    CompanyResponseDTO toResponseDTO(Company company);
}
