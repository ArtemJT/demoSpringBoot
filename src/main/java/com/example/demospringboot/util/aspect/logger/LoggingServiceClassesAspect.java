package com.example.demospringboot.util.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.example.demospringboot.util.aspect.logger.LogANSIConstants.*;

@Slf4j
@Aspect
@Component
public class LoggingServiceClassesAspect {

    @Pointcut("within(com.example.demospringboot.service.impl..*)")
    public void callAtAllServicePublicMethods() {
    }

    @Before("callAtAllServicePublicMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String argsLog = args.length > 0 ? "Args count - " + args.length : "";
        log(methodName, "start. ", argsLog);
    }

    @AfterReturning(value = "callAtAllServicePublicMethods()", returning = "returningValue")
    public void logAfter(JoinPoint joinPoint, Object returningValue) {
        String methodName = joinPoint.getSignature().toShortString();
        StringBuilder message = new StringBuilder();
        if (returningValue != null) {
            message.append(" Returns - ");
            if (returningValue instanceof Collection<?> returningCollect) {
                message.append("Collection size - ").append(returningCollect.size());
            } else if (returningValue instanceof byte[]) {
                message.append("File as byte[]");
            } else {
                message.append(returningValue);
            }
        }
        log(methodName, "end. ", message.toString());
    }

    private void log(String methodName, String message, String addictMessage) {
        log.debug(ANSI_BLUE + "Service: " + methodName + " - " + message + addictMessage + ANSI_RESET);
    }
}
