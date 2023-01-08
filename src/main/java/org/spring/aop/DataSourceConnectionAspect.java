package org.spring.aop;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.spring.invocationHandler.ConnectionInvocationHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Component
@Aspect
public class DataSourceConnectionAspect {

    // invoke this method when there is a call to getConnection(), close(), commit(), rollback() of a Connection Object I want to see when
    // internally these methods are getting called when my app runs
    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println(methodName + "() method is being called for transaction !!");

        Object connectionObject = proceedingJoinPoint.proceed(); // getConnection() object will be returned from here
        System.out.println("the connection value is : " + connectionObject);

        // creating proxy to intercept ConnectionImpl to see how commit(), rollback(), close() works
        Connection con = (Connection) Proxy.newProxyInstance(
                    ConnectionImpl.class.getClassLoader(), // implementation class
                    new Class[] {Connection.class}, // interface
                    new ConnectionInvocationHandler((Connection) connectionObject) // invocation handler
                );

        System.out.println("== Connection is : " + con);
        return con;
    }
}
