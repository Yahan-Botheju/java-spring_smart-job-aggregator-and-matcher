package lk.job_finder_app.smart_job_aggregator.web.user.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    //inject user repo
    private final UserRepository userRepository;

    private final Map<Long, Integer> userRequests = new ConcurrentHashMap<>();

    //get user limit related to role
    @Transactional
    public int getLimitForUser(Long userId){
        return userRepository.findByIdWithSkills(userId)
                .map(user -> user.getRole().getLimitPerMinute()).orElse(5);

    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        //get header
        String uri = request.getHeader("X-User-Id");

        //validate
        if (uri == null) return true;

        //get user ID from header
        Long userId = Long.valueOf(uri);

        //get user limit from custom function
        int limit = getLimitForUser(userId);

        //req count check
        int currentCount = userRequests.getOrDefault(userId, 0);

        if(currentCount >= limit){
            response.setStatus(429);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Too many requests\", \"status\": 429}");
            return false;
        }

        userRequests.put(userId, currentCount + 1);
        return true;

    }

    //clear counts
    @Scheduled(fixedRate = 60000)
    public void resetRequestCounts(){
        userRequests.clear();
    }
}
