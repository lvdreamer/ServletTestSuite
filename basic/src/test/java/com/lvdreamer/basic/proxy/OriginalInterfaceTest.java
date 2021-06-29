package com.lvdreamer.basic.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class OriginalInterfaceTest {

    @Test
    public void sayHello() {
        OriginalInterface originalInterface = new OriginalObject();
        OriginalProxy originalProxy = new OriginalProxy(originalInterface);
        OriginalInterface proxyInstance = (OriginalInterface) Proxy.newProxyInstance(originalProxy.getClass().getClassLoader(), originalInterface.getClass().getInterfaces(), originalProxy);
        proxyInstance.sayHello();
        System.out.println("=======================================================================");
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            System.out.println("anonymous proxy before");
            method.invoke(originalInterface, args);
            System.out.println("anonymous proxy after");
            return null;
        };
        OriginalInterface proxyInstance2 = (OriginalInterface) Proxy.newProxyInstance(originalProxy.getClass().getClassLoader(), originalInterface.getClass().getInterfaces(), invocationHandler);
        proxyInstance2.sayHello();
    }




}