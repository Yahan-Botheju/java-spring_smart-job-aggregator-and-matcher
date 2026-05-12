package lk.job_finder_app.smart_job_aggregator.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.models.enums.RoleName;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class SecurityInterceptor implements HandlerInterceptor {

    //inject user repo
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        //check controller method or not
        if(!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        //check method has @Auth annotation
        Authorize authorize = handlerMethod.getMethodAnnotation(Authorize.class);

        //if not let in as public method
        if(authorize == null) {
            return true;
        }

        //check user role
        String userIdStr = request.getHeader("X-User-Id");
        //validate user role
        if(userIdStr == null) {
            throw new UnauthorizedException("Unauthorized : user ID missing in header");
        }

        //check user availability from db
        User user = userRepository.findByIdWithSkills(Long.valueOf(userIdStr))
                .orElseThrow(() -> new UnauthorizedException("user not found"));

        //check user role has authorization
        RoleName userRole = user.getRole().getRoleName();
        boolean isAuthorized = Arrays.asList(authorize.value()).contains(userRole);

        //validate user auth
        if(!isAuthorized) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied : you do not have permission to access this resource");
            return false;
        }
        return true;
    }


}
