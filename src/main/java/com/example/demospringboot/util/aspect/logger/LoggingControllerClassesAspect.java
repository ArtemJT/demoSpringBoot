package com.example.demospringboot.util.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.demospringboot.util.aspect.logger.LogANSIConstants.ANSI_GREEN;
import static com.example.demospringboot.util.aspect.logger.LogANSIConstants.ANSI_RESET;

@Slf4j
@Aspect
@Component
public class LoggingControllerClassesAspect {

    @Pointcut("within(com.example.demospringboot.web..*)")
    public void callAtAllControllersPublicMethods() {
    }

    @Pointcut("execution(public * com.example.demospringboot.web.LoaderController.*(..))")
    public void callAtLoaderControllerPublicMethods() {
    }

    @Before("callAtAllControllersPublicMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log(methodName, "start.");
    }

    @After(value = "callAtAllControllersPublicMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log(methodName, "end.");
    }

    @AfterReturning(value = "callAtLoaderControllerPublicMethods()", returning = "message")
    public void logAfterLoader(JoinPoint joinPoint, String message) {
        String methodName = joinPoint.getSignature().toShortString();
        log(methodName, message);
    }

    private void log(String methodName, String message) {
        log.info(ANSI_GREEN + "Controller: " + methodName + " - " + message + ANSI_RESET);
    }
}
