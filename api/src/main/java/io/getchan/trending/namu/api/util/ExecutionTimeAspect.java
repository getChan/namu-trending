package io.getchan.trending.namu.api.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class ExecutionTimeAspect {
    private static final StopWatch stopWatch = new StopWatch();

    @Around("@annotation(io.getchan.trending.namu.api.util.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        stopWatch.start();
        final Object proceed = joinPoint.proceed();
        log.info(stopWatch.shortSummary());
        return proceed;
    }
}
