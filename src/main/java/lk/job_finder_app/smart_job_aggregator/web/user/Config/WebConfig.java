package lk.job_finder_app.smart_job_aggregator.web.user.Config;

import lk.job_finder_app.smart_job_aggregator.web.security.SecurityInterceptor;
import lk.job_finder_app.smart_job_aggregator.web.user.interceptor.RateLimitInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //inject rate limit interceptor
    private final RateLimitInterceptor rateLimitInterceptor;

    //inject security interceptor
    private final SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(
            @NonNull InterceptorRegistry registry
    ){
        //register rate limit interceptor
        registry.addInterceptor(rateLimitInterceptor);

        //register security interceptor
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/api/v1/**");
    }

}
