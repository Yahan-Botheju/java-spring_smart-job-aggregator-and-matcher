package lk.job_finder_app.smart_job_aggregator.web.Config;

import lk.job_finder_app.smart_job_aggregator.web.user.interceptor.RateLimitInterceptor;
import lk.job_finder_app.smart_job_aggregator.web.user.interceptor.UserInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //inject user interceptor
    private final UserInterceptor userInterceptor;

    private final RateLimitInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(
            @NonNull InterceptorRegistry registry
    ){
         //register rate limit interceptor
        registry.addInterceptor(rateLimitInterceptor);
    }

}
