package org.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceConnectionAspect {

    // invoke this method when there is a call to getConnection(), close(), commit(), rollback() of a Connection Object I want to see when
    // internally these methods are getting called when my app runs
    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println(methodName + "() method is being called for transaction !!");

        Object returnedValue = proceedingJoinPoint.proceed();
        System.out.println("the connection value is : " + returnedValue);

        return returnedValue;
    }
}
