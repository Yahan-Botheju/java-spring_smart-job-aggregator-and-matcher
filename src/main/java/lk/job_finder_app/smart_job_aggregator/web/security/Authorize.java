package lk.job_finder_app.smart_job_aggregator.web.security;

import lk.job_finder_app.smart_job_aggregator.domain.models.enums.RoleName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//use in method top
@Target(ElementType.METHOD)
//read in app run time
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {
    //roles
    RoleName[]  value();
}
