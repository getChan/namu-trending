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

    @Around("@annotation(io.getchan.trending.namu.api.util.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final Object proceed;
        try {
            proceed = joinPoint.proceed();
            return proceed;
        } finally {
            stopWatch.stop();
            log.info("{} : {} ms", joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());
        }

    }
}
