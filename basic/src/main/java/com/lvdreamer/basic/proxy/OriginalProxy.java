package com.lvdreamer.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OriginalProxy implements InvocationHandler {

    private OriginalInterface originalInterface;

    public OriginalProxy(OriginalInterface originalInterface) {
        this.originalInterface = originalInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy before");
        method.invoke(originalInterface, args);
        System.out.println("proxy after");
        return null;
    }
}
