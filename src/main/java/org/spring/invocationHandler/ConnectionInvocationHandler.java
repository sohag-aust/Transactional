package org.spring.invocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {

    private Connection connection; // target object

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if(method.getName().contains("commit") || method.getName().contains("rollback") || method.getName().contains("close")) {
            System.out.println("**** Connection Trace : " + method.toGenericString());
//        }
        Object returnedValue = method.invoke(connection, args); // method = the method which is being called (commit() / rollback() / close())
        return returnedValue;
    }
}
