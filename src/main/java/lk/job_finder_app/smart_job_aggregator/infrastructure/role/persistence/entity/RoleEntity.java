package lk.job_finder_app.smart_job_aggregator.infrastructure.role.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lk.job_finder_app.smart_job_aggregator.domain.models.enums.RoleName;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;


@Entity
@Table(name = "roles")
@SoftDelete(columnName = "is_deleted")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotBlank(message = "Role cannot be empty")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    private int limitPerMinute;

}
