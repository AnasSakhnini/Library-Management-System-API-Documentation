package com.example.librarymanagementsystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.librarymanagementsystem.service.BookService.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Method called: {}", joinPoint.getSignature().toShortString());
    }

    @Around("execution(* com.example.librarymanagementsystem.service.BookService.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("Method execution time: {} ms", executionTime);

        return result;
    }

    @Before("execution(* com.example.librarymanagementsystem.service.BookService.addBook(..))")
    public void logBookAddition(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Adding book: Title - {}, Author - {}", args);
    }

    @Before("execution(* com.example.librarymanagementsystem.service.BookService.updateBook(..))")
    public void logBookUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Updating book: ID - {},  Updated Book - {}", args[0], args[1]);
    }


    @Before("execution(* com.example.librarymanagementsystem.service.PatronService.*(..))")
    public void logPatronOperation(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("Patron operation: {}", joinPoint.getSignature().toShortString());

    }

    @Around("execution(* com.example.librarymanagementsystem.service.PatronService.*(..))")
    public Object logPatronOperationTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("Patron operation time: {} ms", executionTime);

        return result;
    }
}