package org.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
// aspect question about which code you want to brought here ?
// ans: non functional code brought to be here
// before aspect, code design like : client app -> service
// after aspect, code design like : client app -> aspect -> service (aspect is handled by dynamic proxy)
public class CallTracker {

    @Pointcut("within(org.spring.service.*)")
    public void methodToBeCalled() {}

    @Before("methodToBeCalled()")
    public void logBeforeMethodStartWorking() {
        System.out.println("AOP :: Method started !!");
    }

    @After("methodToBeCalled()")
    public void logAfterMethodCompletedWorking() {
        System.out.println("AOP :: Method completed !!");
    }

    @Around("methodToBeCalled()")
    public void logAroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // before method call
        System.out.println("AOP :: Around Advice :: Before method call");

        // call the method
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("AOP :: Around Advice :: " + methodName + "() method is being called !!");
        Object returnedValueFromMethod = proceedingJoinPoint.proceed(); // proceed means execute the method

        // after method call
        System.out.println("AOP :: Around Advice :: After method call with result : " + returnedValueFromMethod);
    }
}


